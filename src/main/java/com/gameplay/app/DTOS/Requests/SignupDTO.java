package com.gameplay.app.DTOS.Requests;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SignupDTO {
    private String name;
    private String username;
    private String email;
    private String password;
}
