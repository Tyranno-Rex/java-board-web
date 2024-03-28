package com.mysite.sbb.user;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.dao.DataIntegrityViolationException;
import lombok.RequiredArgsConstructor;

import com.mysite.sbb.user.UserFindFrom;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signup(UserCreateForm userCreateForm) {
        return "signup_form";
    }
    @PostMapping("/signup")
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup_form";
        }

        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "signup_form";
        }

        try {
            userService.create(userCreateForm.getUsername(),
                    userCreateForm.getEmail(), userCreateForm.getPassword1());
        }catch(DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "signup_form";
        }catch(Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup_form";
        }

        return "redirect:/";
    }
    @GetMapping("/login")
    public String login() {
        return "login_form";
    }
    @GetMapping("/logout")
    public String logout() {
        return "logout_form";
    }
    @GetMapping("/find_password")
    public String findPassword() {
        return "find_password_form";
    }
    @PostMapping("/find_password")
    public String findPassword(@Valid UserFindFrom userFindForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "find_password_form";
        }
        try {
            if (userService.findPassword(userFindForm.getUsername(), userFindForm.getEmail()) == null) {
                bindingResult.reject("findPasswordFailed", "사용자 정보가 일치하지 않습니다.");
                return "find_password_form";
            } else {
                return "redirect:/";
            }
        }catch(DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("findPasswordFailed", "사용자 정보가 일치하지 않습니다.");
            return "find_password_form";
        }catch(Exception e) {
            e.printStackTrace();
            bindingResult.reject("findPasswordFailed", e.getMessage());
            return "find_password_form";
        }
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }
}



