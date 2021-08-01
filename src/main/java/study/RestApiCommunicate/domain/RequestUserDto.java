package study.RestApiCommunicate.domain;

import lombok.Data;
import study.RestApiCommunicate.entity.User;

@Data
public class RequestUserDto {

    private String username;
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
