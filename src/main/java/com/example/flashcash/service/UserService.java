package com.example.flashcash.service;

import com.example.flashcash.model.Link;
import com.example.flashcash.model.User;
import com.example.flashcash.model.UserAccount;
import com.example.flashcash.repository.AccountRepository;
import com.example.flashcash.repository.LinkRepository;
import com.example.flashcash.repository.UserRepository;
import com.example.flashcash.service.form.AddCashForm;
import com.example.flashcash.service.form.AddIBANForm;
import com.example.flashcash.service.form.LinksForm;
import com.example.flashcash.service.form.SignUpForm;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final SessionService sessionService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final LinkRepository linkRepository;

        public UserService(SessionService sessionService, PasswordEncoder passwordEncoder, UserRepository userRepository, AccountRepository accountRepository, LinkRepository linkRepository) {
        this.sessionService = sessionService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.linkRepository = linkRepository;
    }

    public User registration(SignUpForm form) {
        User user = new User();
        UserAccount account = new UserAccount();
        account.setAmount(0.00);
        user.setAccount(account);
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setEmail(form.getEmail());
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        return userRepository.save(user);
    }

    public UserAccount ibansubmit(AddIBANForm form) {
            UserAccount account = sessionService.sessionUser().getAccount();
            account.setIban(form.getIban());
            return accountRepository.save(account);
    }

    public UserAccount cashAdd(AddCashForm form) {
        UserAccount account = sessionService.sessionUser().getAccount();
        Double amount = sessionService.sessionUser().getAccount().getAmount();
        account.setAmount(form.getAmountAdded() + amount);
        return accountRepository.save(account);
    }

    public Link userLinks(LinksForm form) {
        Link link = new Link();
        link.setUser1(sessionService.sessionUser());
        link.setUser2(userRepository.findUserByEmail(form.getEmail()).get());
        return linkRepository.save(link);
    }
}