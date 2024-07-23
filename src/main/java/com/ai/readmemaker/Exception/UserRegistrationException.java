package com.ai.readmemaker.Exception;

import lombok.Getter;

@Getter
public class UserRegistrationException extends RuntimeException {
    private String errorType = "";

    public UserRegistrationException(String message) {
        super(message);
        if (message.equals("이미 사용 중인 이메일입니다.")) {
            this.errorType = "email";
        }
        if (message.equals("비밀번호가 일치하지 않습니다.") || message.equals("비밀번호는 8자 이상이어야 합니다.")) {
            this.errorType = "password";
        }
    }

}