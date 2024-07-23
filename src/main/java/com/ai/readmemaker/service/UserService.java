package com.ai.readmemaker.service;

import com.ai.readmemaker.Exception.UserRegistrationException;
import com.ai.readmemaker.domain.User;
import com.ai.readmemaker.dto.UserForm;
import com.ai.readmemaker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void save(UserForm userForm) {
        // 이메일 중복 검사
        if (userRepository.findByEmail(userForm.getEmail()).isPresent()) {
            throw new UserRegistrationException("이미 사용 중인 이메일입니다.");
        }

        // 비밀번호 일치 확인
        if (!userForm.getPassword().equals(userForm.getConfirmPassword())) {
            throw new UserRegistrationException("비밀번호가 일치하지 않습니다.");
        }

        // 비밀번호 복잡성 검사 (예: 8자 이상)
        if (userForm.getPassword().length() < 8) {
            throw new UserRegistrationException("비밀번호는 8자 이상이어야 합니다.");
        }

        User user = new User(userForm);
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));
        userRepository.save(user);
    }
}
