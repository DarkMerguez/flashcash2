package com.example.flashcash.controller;


import com.example.flashcash.model.Link;
import com.example.flashcash.model.Transfer;
import com.example.flashcash.model.User;
import com.example.flashcash.repository.LinkRepository;
import com.example.flashcash.repository.TransferRepository;
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

    private final TransferRepository transferRepository;

    public UserController(UserService userService, SessionService sessionService, LinkRepository linkRepository, TransferRepository transferRepository) {
        this.userService = userService;
        this.sessionService = sessionService;
        this.linkRepository = linkRepository;
        this.transferRepository = transferRepository;
    }

    @GetMapping("/signup")
    public ModelAndView showRegisterForm() {
        return new ModelAndView("signup", "signUpForm", new SignUpForm());
    }

    @GetMapping("/")
    public ModelAndView home(Model model) {
        User user = sessionService.sessionUser();
        model.addAttribute("user", user);
        List<Transfer> transfers = transferRepository.findTransfersByFromId(user.getId());

        if (transfers.size() >= 1) {
            int lastIdx = transfers.size() - 1;
            model.addAttribute("lastTransfer", transfers.get(lastIdx));
        }
        if (transfers.size() >= 2) {
            int secondLastIdx = transfers.size() - 2;
            model.addAttribute("secondLastTransfer", transfers.get(secondLastIdx));
        }
        if (transfers.size() >= 3) {
            int thirdLastIdx = transfers.size() - 3;
            model.addAttribute("thirdLastTransfer", transfers.get(thirdLastIdx));
        }

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
        List <Transfer> transfers = transferRepository.findTransfersByFromId(user.getId());
        if (transfers.size() >= 1) {
            int lastIdx = transfers.size() - 1;
            model.addAttribute("lastTransfer", transfers.get(lastIdx));
        }
        if (transfers.size() >= 2) {
            int secondLastIdx = transfers.size() - 2;
            model.addAttribute("secondLastTransfer", transfers.get(secondLastIdx));
        }
        if (transfers.size() >= 3) {
            int thirdLastIdx = transfers.size() - 3;
            model.addAttribute("thirdLastTransfer", transfers.get(thirdLastIdx));
        }
        return new ModelAndView("profile");
    }



    @GetMapping("/addCash")
    public ModelAndView addCash() {
        return new ModelAndView("addCash", "addCashForm", new AddCashForm());
    }

    @GetMapping("/addToBank")
    public ModelAndView addToBank() {
        return new ModelAndView("addToBank", "addCashForm", new AddCashForm());
    }

    @PostMapping("/addToBank")
    public ModelAndView processRequest2(@ModelAttribute("addCashForm") AddCashForm form, Model model ) {
        userService.cashAddToBank(form);
        User user = sessionService.sessionUser();
        model.addAttribute("user", user);
        return new ModelAndView("index");
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
        List <Transfer> transfers = transferRepository.findTransfersByFromId(user.getId());
        if (transfers.size() >= 1) {
            int lastIdx = transfers.size() - 1;
            model.addAttribute("lastTransfer", transfers.get(lastIdx));
        }
        if (transfers.size() >= 2) {
            int secondLastIdx = transfers.size() - 2;
            model.addAttribute("secondLastTransfer", transfers.get(secondLastIdx));
        }
        if (transfers.size() >= 3) {
            int thirdLastIdx = transfers.size() - 3;
            model.addAttribute("thirdLastTransfer", transfers.get(thirdLastIdx));
        }
        return new ModelAndView("index");
    }

    @GetMapping("/transfer")
    public ModelAndView transfer(Model model) {
        User user = sessionService.sessionUser();
        model.addAttribute("user", user);
        List<Link> userLinks = linkRepository.findLinksByUser1Email(user.getEmail());
        model.addAttribute("userLinks", userLinks);
        Transfer transfer = new Transfer();
        model.addAttribute("transfer", transfer);
        return new ModelAndView("transfer");
    }

    @PostMapping("/transfer")
    public ModelAndView processRequest(@ModelAttribute("transferForm") TransferForm form, Model model) {
        userService.transfer(form);
        Transfer transfer = new Transfer();
        model.addAttribute("transfer", transfer);
        User user = sessionService.sessionUser();
        model.addAttribute("user", user);
        return new ModelAndView("transferOK");
    }
    @PostMapping("/add-friend")
    public ModelAndView processRequest2(@ModelAttribute("linksForm") LinksForm form, Model model) {
        userService.userLinks(form);
        User user = sessionService.sessionUser();
        model.addAttribute("user", user);
        return new ModelAndView("addFriendOK");
    }



    @GetMapping("/contact")
    public ModelAndView showContact() {
        return new ModelAndView("contact");
    }
}