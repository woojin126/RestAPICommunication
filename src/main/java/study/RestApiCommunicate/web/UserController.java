package study.RestApiCommunicate.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.RestApiCommunicate.domain.RequestUserDto;
import study.RestApiCommunicate.domain.ResponseUserDto;
import study.RestApiCommunicate.entity.User;
import study.RestApiCommunicate.repository.UserRepository;
import study.RestApiCommunicate.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;


    //스프링은 응답할때 메세지 컨버터를 사용하고, 기본응답이 JSON
    @GetMapping("/user")
    public ResponseUserDto<List<User>> findAll(){
        return new ResponseUserDto<>(HttpStatus.OK.value(),userRepository.findAll());//MessageConverter( JavaObject -> Json String)
    }

    /**
     * // 좋음
     * Optional<Member> member = ...;
     * return member.orElseGet(Member::new);  // member에 값이 없을 때만 new Member()가 실행됨
     */
    @GetMapping("/user/{id}")
    public ResponseUserDto<User> findById(@PathVariable Long id){

        return new ResponseUserDto<>(HttpStatus.OK.value(),userRepository.findById(id).orElseThrow(() -> new IllegalStateException("아이디가없어요~")));

    }

    @CrossOrigin
    @PostMapping("/user")
    //x-www-form-urlencoded => request.getParameter()
    //text/plain => @RequestBody 어노테이션
    //application/json => @RequestBody 어노테이션 + 오브젝트로 받기
    public ResponseUserDto<String> save(@RequestBody RequestUserDto user){
        log.info("userData={}",user);
        //return userRepository.save(user.toEntity());
        return new ResponseUserDto<>(HttpStatus.CREATED.value(),"ok");
    }

    @DeleteMapping("/user/{id}")
    public ResponseUserDto<String> delete(@PathVariable Long id){

        userRepository.deleteById(id);
        return new ResponseUserDto<>(HttpStatus.OK.value());
    }

    @PutMapping("/user/{id}")
    public ResponseUserDto<User> update(@PathVariable Long id,  @RequestBody RequestUserDto user){

        return new ResponseUserDto<>(HttpStatus.OK.value(),userService.update(id,user));

    }

}
