package com.sapo.controller;

import com.sapo.dto.Account;
import com.sapo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountRepository tutorialRepository;

    @PostMapping("/insert")
    public Account insertAccount(@RequestBody Account account) {
        return tutorialRepository.save(account);
    }

    @GetMapping("/getAll")
    public List<Account> getAllAccount() {
        return tutorialRepository.findAll();
    }
}
