package com.ai.readmemaker.controller;

import com.ai.readmemaker.Exception.UserRegistrationException;
import com.ai.readmemaker.domain.User;
import com.ai.readmemaker.dto.UserForm;
import com.ai.readmemaker.dto.response.LoginResponse;
import com.ai.readmemaker.service.UserRestService;
import com.ai.readmemaker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserRestController {

    private final UserRestService userRestService;

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserForm userForm) {
        // 로그인 처리 로직 (일반적으로 토큰 발급 등)
        LoginResponse loginResponse = userRestService.login(userForm.getEmail(), userForm.getPassword());
        return ResponseEntity.ok(loginResponse);
    }

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Validated UserForm userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        try {
            User savedUser = userRestService.save(userForm);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (UserRegistrationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

//    // 모든 사용자 조회
//    @GetMapping("/users")
//    public ResponseEntity<List<UserForm>> getAllUsers() {
//        List<UserForm> users = userService.findAll();
//        return ResponseEntity.ok(users);
//    }
//
//    // 특정 사용자 조회
//    @GetMapping("/users/{id}")
//    public ResponseEntity<?> getUser(@PathVariable Long id) {
//        try {
//            UserForm user = userService.findById(id);
//            return ResponseEntity.ok(user);
//        } catch (UserNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//    }
//
//    // 사용자 정보 수정
//    @PutMapping("/users/{id}")
//    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody @Validated UserForm userForm, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
//        }
//
//        try {
//            UserForm updatedUser = userService.update(id, userForm);
//            return ResponseEntity.ok(updatedUser);
//        } catch (UserNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        } catch (UserRegistrationException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
//
//    // 사용자 삭제
//    @DeleteMapping("/users/{id}")
//    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
//        try {
//            userService.delete(id);
//            return ResponseEntity.ok("User deleted successfully");
//        } catch (UserNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
//    }
//
//    // 사용자 검색
//    @GetMapping("/users/search")
//    public ResponseEntity<List<UserForm>> searchUsers(@RequestParam String keyword) {
//        List<UserForm> users = userService.searchByKeyword(keyword);
//        return ResponseEntity.ok(users);
//    }
}