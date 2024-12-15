package com.i3t.expanse.controller;

import com.i3t.expanse.model.Transaction;
import com.i3t.expanse.model.User;
import com.i3t.expanse.repository.UserRepository;
import com.i3t.expanse.security.CustomUserDetailsService;
import com.i3t.expanse.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
private static final Logger log = LoggerFactory.getLogger(TransactionController.class);
    @Autowired
    private TransactionService transactionService;
    
    @Autowired
    UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            log.info("******** username is {}",auth.getName());
            User user = userRepository.findByUserName(auth.getName());
            log.info("******** user is {}",user);
            transaction.setUser(user);
            return new ResponseEntity<>(transactionService.createTransaction(transaction, user.getId()), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating transaction: ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
