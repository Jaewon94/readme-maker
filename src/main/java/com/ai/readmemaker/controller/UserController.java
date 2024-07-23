package com.ai.readmemaker.controller;

import com.ai.readmemaker.Exception.UserRegistrationException;
import com.ai.readmemaker.dto.UserForm;
import com.ai.readmemaker.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;
    private final AuthenticationManager authenticationManager;


//    로그인 화면

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }
//
////    로그아웃
//    @GetMapping("/logout")
//    public String logout(HttpSession session) {
//        session.invalidate();
//        return "redirect:/";
//    }

//    회원가입 화면
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userForm",new UserForm());
        return "user/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("userForm") @Validated UserForm userForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "user/register";
        }

        try {
            userService.save(userForm);
            return "redirect:/index";
        } catch (UserRegistrationException e) {
            bindingResult.rejectValue(e.getErrorType(), "error.userForm", e.getMessage());
            return "user/register";
        }
    }
}
