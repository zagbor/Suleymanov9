package ru.zagbor.practice.suleimanov.service.impl;

import ru.zagbor.practice.suleimanov.model.AccountStatus;
import ru.zagbor.practice.suleimanov.repository.AccountStatusRepository;
import ru.zagbor.practice.suleimanov.repository.impl.AccountStatusRepositoryImpl;
import ru.zagbor.practice.suleimanov.service.AccountStatusService;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

public class AccountStatusServiceImpl implements AccountStatusService {
    AccountStatusRepository accountStatusRepository = new AccountStatusRepositoryImpl();

    @Override

    public List<AccountStatus> getAll() {
        return accountStatusRepository.getAll();
    }

    @Override
    public Optional<AccountStatus> getByID(long id) {
        AccountStatus accountStatus = null;
        if (accountStatusRepository.isAccountStatusExist(id)) {
            accountStatus = accountStatusRepository.getById(id).orElseThrow(() -> new RuntimeException(format("Accountstatus [%s] not exists", id)));
        }
        return Optional.of(accountStatus);
    }

    @Override
    public boolean isAccountStatusExist(long id) {
        return accountStatusRepository.isAccountStatusExist(id);
    }
}
