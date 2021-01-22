package ru.zagbor.practice.suleimanov.service;

import ru.zagbor.practice.suleimanov.model.Account;
import ru.zagbor.practice.suleimanov.model.AccountStatus;

public interface AccountService {

    void changeAccountStatus(Account account, AccountStatus accountStatus);
}
