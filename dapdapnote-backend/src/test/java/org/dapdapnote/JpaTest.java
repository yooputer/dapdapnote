package org.dapdapnote;

import org.dapdapnote.entity.User;
import org.dapdapnote.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class JpaTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateUser() {
        User user = userRepository.findByUserId("yoojin027")
                .orElse(User.builder()
                        .userId("yoojin027")
                        .name("김유진")
                        .password("12345678")
                        .build()
                );

        if (user.getSeq() == null) {
            userRepository.save(user);
        }
    }
}
