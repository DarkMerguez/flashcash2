package com.example.flashcash.controller;


import com.example.flashcash.model.Link;
import com.example.flashcash.model.Transfer;
import com.example.flashcash.model.User;
import com.example.flashcash.repository.LinkRepository;
import com.example.flashcash.service.SessionService;
import com.example.flashcash.service.UserService;
import com.example.flashcash.service.form.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class UserController {

    private final UserService userService;
    private final SessionService sessionService;
    private final LinkRepository linkRepository;

    public UserController(UserService userService, SessionService sessionService, LinkRepository linkRepository) {
        this.userService = userService;
        this.sessionService = sessionService;
        this.linkRepository = linkRepository;
    }

    @GetMapping("/signup")
    public ModelAndView showRegisterForm() {
        return new ModelAndView("signup", "signUpForm", new SignUpForm());
    }

    @GetMapping("/")
    public ModelAndView home(Model model) {
        User user = sessionService.sessionUser();
        model.addAttribute("user", user);
        return new ModelAndView("index");
    }
    @GetMapping("/addIBAN")
    public ModelAndView addIBAN() {
        return new ModelAndView("addIBAN","addIBANForm", new AddIBANForm());
    }

    @GetMapping("/profile")
    public ModelAndView profile(Model model) {
        User user = sessionService.sessionUser();
        model.addAttribute("user", user);
        return new ModelAndView("profile");
    }



    @GetMapping("/addCash")
    public ModelAndView addCash() {
        return new ModelAndView("addCash", "addCashForm", new AddCashForm());
    }

    @PostMapping("/addIBAN")
    public ModelAndView processRequest(@ModelAttribute("addIBANForm") AddIBANForm form, Model model) {
        userService.ibansubmit(form);
        User user = sessionService.sessionUser();
        model.addAttribute("user", user);
        return new ModelAndView("index");
    }

    @PostMapping("/signup")
    public ModelAndView processRequest(@ModelAttribute("signUpForm") SignUpForm form ) {
        userService.registration(form);
        return new ModelAndView("signin");
    }

    @PostMapping("/addCash")
    public ModelAndView processRequest(@ModelAttribute("addCashForm") AddCashForm form, Model model ) {
        userService.cashAdd(form);
        User user = sessionService.sessionUser();
        model.addAttribute("user", user);
        return new ModelAndView("index");
    }

    @GetMapping("/transfer")
    public ModelAndView transfer(Model model, Model model2) {
        User user = sessionService.sessionUser();
        model.addAttribute("user", user);
        List<Link> userLinks = linkRepository.findLinksByUser1Email(user.getEmail());
        model2.addAttribute("userLinks", userLinks);
        return new ModelAndView("transfer");
    }

    @PostMapping("/transfer")
    public ModelAndView processRequest(@ModelAttribute("linksForm") LinksForm form, Model model, @ModelAttribute("transferForm") TransferForm form2, Model model2) {
        userService.userLinks(form);
        userService.transfer(form2);
        User user = sessionService.sessionUser();
        model.addAttribute("user", user);
        Transfer transfer = new Transfer();
        model2.addAttribute("transfer", transfer);
        return new ModelAndView("index");
    }



    @GetMapping("/contact")
    public ModelAndView showContact() {
        return new ModelAndView("contact");
    }
}