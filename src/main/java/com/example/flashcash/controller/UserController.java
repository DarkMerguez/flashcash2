package com.example.flashcash.controller;

import ch.qos.logback.core.model.Model;
import com.example.flashcash.service.UserService;
import com.example.flashcash.service.form.IndexForm;
import com.example.flashcash.service.form.SignInForm;
import com.example.flashcash.service.form.SignUpForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) { this.userService = userService; }

    @GetMapping("/signup")
    public ModelAndView showRegisterForm() {
        return new ModelAndView("signup", "signUpForm", new SignUpForm());
    }

    @GetMapping("/index")
    public ModelAndView home(Model model) {
        return new ModelAndView("index");
    }

    @PostMapping("/signup")
    public ModelAndView processRequest(@ModelAttribute("signUpForm") SignUpForm form ) {
        userService.registration(form);
        return new ModelAndView("signin");
    }
}