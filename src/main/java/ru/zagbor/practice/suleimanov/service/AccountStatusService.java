package ru.zagbor.practice.suleimanov.service;

import ru.zagbor.practice.suleimanov.model.AccountStatus;

import java.util.List;
import java.util.Optional;

public interface AccountStatusService {
    List<AccountStatus> getAll();

    Optional<AccountStatus> getByID(long id);

    boolean isAccountStatusExist(long id);
}
