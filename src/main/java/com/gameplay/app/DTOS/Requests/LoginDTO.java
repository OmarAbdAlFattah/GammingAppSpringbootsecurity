package com.gameplay.app.DTOS.Requests;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginDTO {
    private String usernameOrEmail;
    private String password;
}
