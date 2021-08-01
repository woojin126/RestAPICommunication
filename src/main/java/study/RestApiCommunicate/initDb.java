package study.RestApiCommunicate;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import study.RestApiCommunicate.entity.User;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
public class initDb {

    private final InitService initService;

    @Autowired
    public initDb(InitService initService) {
        this.initService = initService;
    }

    @PostConstruct
    public void init(){
        System.out.println("InitService");
        initService.dbInit1s();
    }


    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{

        private final EntityManager em;

        public void dbInit1s(){

            User user = createUser("사자","1234","123-123");
            em.persist(user);

            User user1 = createUser("호랑이", "1111", "12-12");
            em.persist(user1);

            User user2 = createUser("원숭이", "2222", "11-11");
            em.persist(user2);

        }

        private User createUser(String username, String password  , String phone) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setPhone(phone);

            return user;
        }
    }
}
