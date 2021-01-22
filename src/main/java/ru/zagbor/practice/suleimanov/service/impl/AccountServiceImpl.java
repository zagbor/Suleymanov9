package ru.zagbor.practice.suleimanov.service.impl;

import ru.zagbor.practice.suleimanov.model.Account;
import ru.zagbor.practice.suleimanov.model.AccountStatus;
import ru.zagbor.practice.suleimanov.repository.AccountRepository;
import ru.zagbor.practice.suleimanov.repository.impl.AccountRepositoryImpl;
import ru.zagbor.practice.suleimanov.service.AccountService;

public class AccountServiceImpl implements AccountService {
    AccountRepository accountRepository = new AccountRepositoryImpl();

    @Override
    public void changeAccountStatus(Account account, AccountStatus accountStatus) {
        account.setAccountStatus(accountStatus);
        accountRepository.update(account);
    }
}
