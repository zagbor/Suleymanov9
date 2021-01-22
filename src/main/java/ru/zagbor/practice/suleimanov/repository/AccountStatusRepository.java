package ru.zagbor.practice.suleimanov.repository;
import ru.zagbor.practice.suleimanov.model.AccountStatus;
import ru.zagbor.practice.suleimanov.model.Customer;

import java.util.List;

public interface AccountStatusRepository extends GenericRepository<AccountStatus, Long> {
    List<AccountStatus> getAll();

    boolean isAccountStatusExist(long id);
}
