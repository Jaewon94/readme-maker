package com.ai.readmemaker.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class UserForm {
    private String email;
    private String password;
    private String confirmPassword;
    private String nickname;
}
