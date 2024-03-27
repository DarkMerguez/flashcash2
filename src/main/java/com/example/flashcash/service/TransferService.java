//package com.example.flashcash.service;
//
//import com.example.flashcash.model.Transfer;
//import com.example.flashcash.model.User;
//import com.example.flashcash.model.UserAccount;
//import com.example.flashcash.repository.AccountRepository;
//import com.example.flashcash.repository.TransferRepository;
//import com.example.flashcash.repository.UserRepository;
//import com.example.flashcash.service.form.TransferForm;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//
//@Service
//public class TransferService {
//
//    private final SessionService sessionService;
//    private final UserRepository userRepository;
//    private final AccountRepository accountRepository;
//    private final TransferRepository transferRepository;
//
//    public TransferService(SessionService sessionService, UserRepository userRepository, AccountRepository accountRepository, TransferRepository transferRepository) {
//        this.sessionService = sessionService;
//        this.userRepository = userRepository;
//        this.accountRepository = accountRepository;
//        this.transferRepository = transferRepository;
//    }
//
//    public Transfer cashTransfer1(TransferForm form) {
//
//        Transfer transfer = new Transfer();
//        transfer.setUser_from(sessionService.sessionUser());
//        transfer.setDateTime(new Date());
//        transfer.setAmount_before_fee(form.getAmountBeforeFee());
//        transfer.setAmount_after_fee(form.getAmountBeforeFee() * 0.95);
//        return transferRepository.save(transfer);
//    }
//
//    public Transfer cashTransfer2(TransferForm form) {
//
//        Transfer transfer = new Transfer();
////        transfer.setUser_to(form.getEmail());
//        transfer.setDateTime(new Date());
//        transfer.setAmount_before_fee(form.getAmountBeforeFee());
//        transfer.setAmount_after_fee(form.getAmountBeforeFee() * 0.95);
//        return transferRepository.save(transfer);
//    }
//
//    public UserAccount cashTransfer3(TransferForm form) {
//
//        UserAccount userAccount = new UserAccount();
//        Transfer transfer = new Transfer();
//
//        userAccount.setAmount(userAccount.getAmount() - transfer.getAmount_after_fee());
//        return accountRepository.save(userAccount);
//    }
//
//
//
//
//}
