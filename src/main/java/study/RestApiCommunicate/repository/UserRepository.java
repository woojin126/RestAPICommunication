package study.RestApiCommunicate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.RestApiCommunicate.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {


}
