package ru.zagbor.practice.suleimanov.repository.impl;

import ru.zagbor.practice.suleimanov.model.AccountStatus;
import ru.zagbor.practice.suleimanov.repository.AccountStatusRepository;

import java.util.List;
import java.util.Optional;

import static ru.zagbor.practice.suleimanov.db.TransactionService.doInTransactionFunc;
import static ru.zagbor.practice.suleimanov.db.TransactionService.doInTransactionVoid;

public class AccountStatusRepositoryImpl implements AccountStatusRepository {


    @Override
    public Optional<AccountStatus> getById(Long id) {
        return doInTransactionFunc(session -> {
            return Optional.of(session.get(AccountStatus.class, id));
        });
    }


    @Override
    public List<AccountStatus> getAll() {
        return doInTransactionFunc(session -> {
            return session.createQuery("FROM AccountStatus", AccountStatus.class).list();
        });
    }

    @Override
    public boolean isAccountStatusExist(long id) {
        return doInTransactionFunc(session -> {
            Optional<AccountStatus> optionalAccountStatus = Optional.ofNullable(session.get(AccountStatus.class, id));
            return optionalAccountStatus.isPresent();
        });
    }

    @Override
    public AccountStatus update(AccountStatus accountStatus) {
        return doInTransactionFunc(session -> {
            session.update(accountStatus);
            return accountStatus;
        });
    }

    @Override
    public AccountStatus create(AccountStatus accountStatus) {
        return doInTransactionFunc(session -> {
            session.save(accountStatus);
            return accountStatus;
        });
    }

    @Override
    public void deleteById(Long id) {
        doInTransactionVoid(session -> {
            AccountStatus accountStatus = new AccountStatus();
            accountStatus.setId(id);
            session.delete(accountStatus);
        });
    }
}
