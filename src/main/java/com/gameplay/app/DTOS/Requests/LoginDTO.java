package com.gameplay.app.DTOS.Requests;

import lombok.*;
import org.springframework.stereotype.Component;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class LoginDTO {
    private String usernameOrEmail;
    private String password;
}
