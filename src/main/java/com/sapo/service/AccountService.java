package com.sapo.service;

import com.sapo.dto.Account;
import com.sapo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        List<Account> accounts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Account account = new Account();
            account.setSpentMoney(new BigDecimal("0"));
            accounts.add(account);
        }
        accountRepository.saveAll(accounts);
    }
}
