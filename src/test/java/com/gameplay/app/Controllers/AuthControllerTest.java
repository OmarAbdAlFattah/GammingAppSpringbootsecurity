package com.gameplay.app.Controllers;

import com.gameplay.app.DTOS.Requests.LoginDTO;
import com.gameplay.app.DTOS.Requests.SignupDTO;
import com.gameplay.app.Entities.Role;
import com.gameplay.app.Entities.User;
import com.gameplay.app.Repos.RoleRepo;
import com.gameplay.app.Repos.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)

class AuthControllerTest {

    @Mock
    RoleRepo roleRepo;
    @Mock
    UserRepo userRepo;

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    AuthController controller;

    @Test
    public void loginUserThroughBody_success() throws Exception {
        LoginDTO loginDto = new LoginDTO("omarUserNameko", "password");
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Authentication authentication = mock(Authentication.class);

        when(authenticationManager.authenticate(any())).thenReturn(authentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        ResponseEntity<String> responseEntity = controller.authenticateUser(loginDto);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void signupUserThroughBody_success(){
        Role role = new Role(1L,"ROLE_ADMIN");
        Set<Role> roles = new HashSet<>();

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        SignupDTO signupDto = new SignupDTO("omar",
                "omarUserNameko",
                "omarMailko@gmail.com",
                "password");

        User user = new User(1L,
                        signupDto.getName(),
                        signupDto.getUsername(),
                        signupDto.getEmail(),
                        signupDto.getPassword(),
                        roles
        );

        try {
        when(userRepo.existsByUsername(signupDto.getUsername())).thenReturn(false);
        when(userRepo.existsByEmail(signupDto.getEmail())).thenReturn(false);
        when(roleRepo.findByName(any())).thenReturn(Optional.of(role));
        } catch (NoSuchElementException nsee) {
            System.err.println("Sorry, Couldn't find Role");
        }

        when(userRepo.save(any())).thenReturn(user);

        ResponseEntity<String> responseEntity = controller.registerUser(signupDto);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }
}