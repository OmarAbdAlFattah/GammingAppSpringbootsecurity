package com.gameplay.app.Services;

import com.gameplay.app.Entities.User;
import com.gameplay.app.Repos.UserRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class UserServiceTest {
    @Mock
    UserRepo userRepo;
    AutoCloseable autoCloseable;
    Optional<User> user;
    UserService userServiceTest;


    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        userServiceTest = new UserService(userRepo);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void loadUserByUsername() {
        String usernameOrEmail = "omarMail@gmail.com";
        given(userRepo.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)).willReturn(Optional.empty());
        //when
        user = userRepo.findByUsername(usernameOrEmail);
        //then
        assertNotNull(user);
    }
}