package com.gameplay.app.Services;

import com.gameplay.app.Entities.Role;
import com.gameplay.app.Entities.User;
import com.gameplay.app.Repos.UserRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepo userRepo;

    @Test
    void loadUserByUsername() {
//        String usernameOrEmail = "omarMail@gmail.com";
        User user1 =new User();
        user1.setUsername("7madaelsawi");
        user1.setName("7mada");
        user1.setEmail("7amada@gmail.com");
        user1.setPassword("password");
        user1.setId(4L);
        user1.setRoles(Set.of(new Role("ROLE_ADMIN")));
        //when
        when(userRepo.findByUsernameOrEmail("7amada@gmail.com", "7amada@gmail.com")).thenReturn(Optional.of(user1));
        UserDetails userDetails= userService.loadUserByUsername(user1.getEmail());
        //then
        assertEquals(user1.getEmail() ,userDetails.getUsername());
    }
}