package study.RestApiCommunicate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.RestApiCommunicate.domain.RequestUserDto;
import study.RestApiCommunicate.entity.User;
import study.RestApiCommunicate.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;


    @Transactional
    public User update(Long id, RequestUserDto dto){
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("값이 없습니다"));

        return User.updateUser(dto, user);

    }



}
