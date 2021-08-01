package study.RestApiCommunicate.entity;

import lombok.*;
import study.RestApiCommunicate.domain.RequestUserDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
@ToString
public class User {

    @Id @GeneratedValue
    private Long id;

    private String username;
    private String password;
    private String phone;


    public static User updateUser(RequestUserDto dto, User user) {
        user.setUsername(dto.toEntity().getUsername());
        user.setPassword(dto.toEntity().getPassword());
        user.setPhone(dto.toEntity().getPhone());

        return user;
    }




}
