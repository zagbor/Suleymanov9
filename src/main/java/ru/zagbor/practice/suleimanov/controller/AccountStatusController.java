package ru.zagbor.practice.suleimanov.controller;

import ru.zagbor.practice.suleimanov.model.AccountStatus;
import ru.zagbor.practice.suleimanov.service.AccountStatusService;
import ru.zagbor.practice.suleimanov.service.impl.AccountStatusServiceImpl;

import java.util.List;
import java.util.Optional;

public class AccountStatusController {
    private final AccountStatusService accountStatusService = new AccountStatusServiceImpl();

    public List<AccountStatus> getAll() {
        return accountStatusService.getAll();
    }

    public Optional<AccountStatus> getByID(long id) {
        return accountStatusService.getByID(id);
    }

    public boolean isAccountStatusExist(long id) {
        return accountStatusService.isAccountStatusExist(id);
    }
}
