package study.RestApiCommunicate.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import study.RestApiCommunicate.domain.ResponseUserDto;
import study.RestApiCommunicate.web.UserController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

//@Controller ,@RestController,@Component, @Configuration 디스패처가 메모리에 올려주는 어노테이션
//컨트롤러 진입하기전에 설정하면 @Configuration /  컨트롤러진입후(매핑후) 설절은그냥 @Component
@Component
@Aspect
@Slf4j
public class BindingAdvice {
    /*
     전체적인 큰틀 앞에서 처리하고싶으면 Filter
     메서드앞뒤에서 처리하고싶으면 AOP
     */

    /*
    @After 후처리 함수가 종료되고나서 로그를남김
     */
/*
    @After("execution(* study.RestApiCommunicate.web..*Controller.save(..))")
    public void testCheckAfter(){
        System.out.println("후처리 로그");
    }
*/

   /*
   @Before 에서는 메서드 처리 전에 , 상황이 발생하는것이라
   ProceedingJoinPoint 매개변수로 사용시 에러. (리플렉션한 메서드들을 가져오는건데 메서드들을 사용할일이없기 때문이다)
   
   void 이어야만함 리턴은 무조건 void 전처리라 후처리를 할수가 없기때문
    */
  /*  @Before("execution(* study.RestApiCommunicate.web..*Controller.save(..))")
    public void testCheckBefore(){
        System.out.println("전처리 로그");
        //전처리가 안되지만 아래메서드 사용해서 할 수도있음
        HttpServletRequest request=
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();//주소 받은값으로 뭔가를하고싶을떄 이걸사용
        System.out.println("request.getRequestURL() = " + request.getRequestURL());
    }*/

    //함수 : 앞 뒤  @Around
    //함수 : 앞 (username이 안들어왔으면 내가 강제로 넣어주고 실행시켜주고싶을때) @Before
    //함수 : 뒤 (응답만 관리)  @After

    // 웹폴더안에있는 모든것들중에 이름이 Controller로 끝나는 모든것들 이라는뜻
    // 마지막*(..) 은 모든 인자들
    //이 validCheck은 아래 경로의 모든컨트롤러의 앞뒤로 실행된다!
    //@Around("execution(* study.RestApiCommunicate.web..*Controller.save(..))")
//update 함수의 앞뒤를 제어해줄것 ,update 앞 뒤를제어 해야 리턴가능
    @Around("@annotation(ValidCheck)")
    public Object validCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String type = proceedingJoinPoint.getSignature().getDeclaringTypeName();
        String method = proceedingJoinPoint.getSignature().getName();
        System.out.println("type = " + type);
        System.out.println("method = " + method);
        Object[] args = proceedingJoinPoint.getArgs(); //save를 호출했으면 RequestUserDto , BingingResult 두개의 인자를호출
        for (Object arg : args) {
            System.out.println("arg = " + arg);
            if (arg instanceof BindingResult) { // BindingResult가 붙어있으면 true
                BindingResult result = (BindingResult) arg; //오브젝트니 다운캐스팅
                if (result.hasErrors()) {
                    Map<String, String> errorMap = new HashMap<>();

                    for (FieldError error : result.getFieldErrors()) {
                        errorMap.put(error.getField(), error.getDefaultMessage());
                        log.warn(type+"."+method+"()=> 필드:"+error.getField()+",메시지:"+error.getDefaultMessage());
                    }

                    return new ResponseUserDto<>(HttpStatus.BAD_REQUEST.value(), errorMap);
                }
            }
        }
        return proceedingJoinPoint.proceed(); // 함수 스택시행
        
    }
}

/*
Log Level
error 에러만뜸
warn  워닝으로 설정시 에러까지뜸
info  인포로설정시 그위에단계까지 실행
debug 다뜸 => 개발단계에서사용
 */