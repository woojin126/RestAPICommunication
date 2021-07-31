# RestAPICommunication
## 목표

스프링 MVC 기반 JPA 방식 사용하여 , RestApi JSON 통신, 요청 응답 확인, validation 체크, 문서로만들기 , 로그남기기

##스팩

### 기본 사양

-spring boot 2.4.5
-Mybatis
-Oracle 11g XE

### Utils dependencies(Gradle)

- thymeleaf
- validation
- starter-data-jpa
- devtools
- lombok
- mybatis


### IDLE

- IntelliJ 구축

## Version

### 1.0 초기 셋팅

IntelliJ 콘솔 로그 한글 깨짐 해결 방법
- IntelliJ File Encodings 변경

1. Ctrl + alt + S
2. Editor > File Encodings 선택
3. 셋팅


### lombok 설정

1. Setting
2. Annotation Processors
3. Enable annotation processing 체크

###그래들 빌드 셋팅

Setting -> Build,Execution,Deployment -> Build Tools -> Gradle
Build and run Using : IntelliJ IDEA 변경
Run tests using : IntelliJ IDEA 변경
