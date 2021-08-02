package study.RestApiCommunicate.domain;

import lombok.Data;
import study.RestApiCommunicate.entity.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * https://dublin-java.tistory.com/5 리플랙션
 */
@Data
public class RequestUserDto {

    @NotNull(message = "유저네임 키값이 없습니다") //키값이 없을떄
    @NotBlank(message = "유저네임 입력") //키는있는데 값이없을때
    @Size(max = 20, message = "길이를 초과했습니다")
    private String username;
    
    @NotNull(message = "비밀번호가없습니다")
    private String password;

    private String phone;


  public User toEntity(){
        return User.builder()
                .username(username)
                .password(password)
                .phone(phone)
                .build();
    }
}
