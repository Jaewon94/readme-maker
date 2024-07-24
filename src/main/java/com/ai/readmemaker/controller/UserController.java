package com.ai.readmemaker.controller;

import com.ai.readmemaker.Exception.UserRegistrationException;
import com.ai.readmemaker.dto.UserForm;
import com.ai.readmemaker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;


//    로그인 화면

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

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
