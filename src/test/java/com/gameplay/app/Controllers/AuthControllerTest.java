package com.gameplay.app.Controllers;

import com.gameplay.app.AppApplication;
import com.gameplay.app.DTOS.Requests.LoginDTO;
import com.gameplay.app.DTOS.Requests.SignupDTO;
import com.gameplay.app.Entities.Role;
import com.gameplay.app.Entities.User;
import com.gameplay.app.Repos.RoleRepo;
import com.gameplay.app.Repos.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = AppApplication.class,
        webEnvironment = WebEnvironment.RANDOM_PORT)
class AuthControllerTest {

    @LocalServerPort
    int randomServerPort;
    @Mock
    RoleRepo roleRepo;
    @Mock
    UserRepo userRepo;

    @Mock
    TestRestTemplate testRestTemplate;

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    AuthController controller;
    @Bean
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }
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

    @Test
    public void loginUserWithBody_success () throws URISyntaxException {
        LoginDTO loginDTO = new LoginDTO("omarUserNameko","password");

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        URI uri = new URI("http://localhost:" + randomServerPort + "/api/auth/signin");

        //ResponseEntity<String> createdCustomer =
                Assertions.assertEquals(testRestTemplate.withBasicAuth(loginDTO.getUsernameOrEmail(),loginDTO.getPassword())
                        .postForEntity(uri, loginDTO,String.class ).getBody(),"User signed-in successfully!.");
    }

    @Test
    public void signupUserWithBody_success() throws URISyntaxException {
//        Role roles = roleRepo.findByName("ROLE_ADMIN").get();
        SignupDTO signupDTO = new SignupDTO(
                "Omar", "omarUserNameko",
                "omarMailko@gmail.com",
                "password"
        );
        URI uri = new URI("http://localhost:" + randomServerPort + "/api/auth/signup");
        ResponseEntity<String> createdCustomer = restTemplate().postForEntity(uri,signupDTO,String.class);
        Assertions.assertEquals(createdCustomer.getBody(),"User registered successfully");
    }
}