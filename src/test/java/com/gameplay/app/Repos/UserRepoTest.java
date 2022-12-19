package com.gameplay.app.Repos;


import com.gameplay.app.AppApplication;
import com.gameplay.app.Entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Transactional
@ContextConfiguration(classes = AppApplication.class)
@DataJpaTest
class UserRepoTest {
    @Autowired
    private UserRepo userRepo;

    @Test
    void existsByUsernameTest() {
        String username = "tipsyomar";
        Long id = 1L;
        User user = new User(id, "Omar", username, "omar@gmail.com");
        userRepo.save(user);

        boolean exists = userRepo.existsByUsername(username);
        assertThat(exists).isTrue();
    }

    @Test
    void existsByEmail() {
        String email = "omar@gmail.com";
        Long id = 1L;
        User user = new User(id, "Omar", "tipsyomar", email);
        userRepo.save(user);

        boolean exists = userRepo.existsByEmail(email);
        assertThat(exists).isTrue();
    }
}