package ru.zagbor.practice.suleimanov.controller;

import ru.zagbor.practice.suleimanov.model.Account;
import ru.zagbor.practice.suleimanov.model.AccountStatus;
import ru.zagbor.practice.suleimanov.service.AccountService;
import ru.zagbor.practice.suleimanov.service.impl.AccountServiceImpl;

public class AccountController {

    private final AccountService accountService = new AccountServiceImpl();

    public AccountController() {
    }

    public void changeAccountStatus(Account account, AccountStatus accountStatus) {
        accountService.changeAccountStatus(account, accountStatus);
    }
}
