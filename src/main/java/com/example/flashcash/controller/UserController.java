package com.example.flashcash.controller;

import com.example.flashcash.service.form.IndexForm;
import com.example.flashcash.service.form.SignInForm;
import com.example.flashcash.service.form.SignUpForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @GetMapping("/signup")
    public ModelAndView showRegisterForm() {
        return new ModelAndView("signup", "signUpForm", new SignUpForm());
    }

    @GetMapping("/signin")
    public ModelAndView showConnectionForm() {
        return new ModelAndView("signin", "signInForm", new SignInForm());
    }

    @GetMapping("/index")
    public ModelAndView showIndex() {
        return new ModelAndView("index", "indexForm", new IndexForm());
    }
}