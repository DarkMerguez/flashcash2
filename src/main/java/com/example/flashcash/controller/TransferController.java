//package com.example.flashcash.controller;
//
//import com.example.flashcash.model.Transfer;
//import com.example.flashcash.model.User;
//import com.example.flashcash.service.LinkService;
//import com.example.flashcash.service.SessionService;
//import com.example.flashcash.service.TransferService;
//import com.example.flashcash.service.UserService;
//import com.example.flashcash.service.form.AddCashForm;
//import com.example.flashcash.service.form.AddIBANForm;
//import com.example.flashcash.service.form.TransferForm;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//@Controller
//public class TransferController {
//
//    private final TransferService transferService;
//    private final LinkService linkService;
//    private final UserService userService;
//    private final SessionService sessionService;
//
//    public TransferController(TransferService transferService, LinkService linkService, UserService userService, SessionService sessionService) {
//        this.transferService = transferService;
//        this.linkService = linkService;
//        this.userService = userService;
//        this.sessionService = sessionService;
//
//    }
//
//    @GetMapping("/transfer")
//    public ModelAndView transfer() {
//        return new ModelAndView("transfer","transferForm", new TransferForm());
//    }
//
//    @PostMapping("/transfer")
//    public ModelAndView processRequest(@ModelAttribute("transferForm") TransferForm form, Model model ) {
//        transferService.cashTransfer1(form);
//        User user = sessionService.sessionUser();
//        model.addAttribute("user", user);
//        return new ModelAndView("index");
//    }
//}
