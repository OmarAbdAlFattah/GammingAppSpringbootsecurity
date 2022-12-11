package com.gameplay.app.Controllers;

import com.gameplay.app.DTOS.Requests.LoginDTO;
import com.gameplay.app.Repos.RoleRepo;
import com.gameplay.app.Repos.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)

class AuthControllerTest {

    private MockMvc mvc;

    @Mock
    RoleRepo roleRepo;
    UserRepo userRepo;

    @Mock
    AuthenticationManager authenticationManager;
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
}