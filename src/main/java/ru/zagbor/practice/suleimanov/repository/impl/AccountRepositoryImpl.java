package ru.zagbor.practice.suleimanov.repository.impl;


import org.hibernate.SessionFactory;
import ru.zagbor.practice.suleimanov.db.HibernateSessionFactory;
import ru.zagbor.practice.suleimanov.model.Account;
import ru.zagbor.practice.suleimanov.repository.AccountRepository;

import java.util.List;
import java.util.Optional;

import static ru.zagbor.practice.suleimanov.db.TransactionService.doInTransactionFunc;
import static ru.zagbor.practice.suleimanov.db.TransactionService.doInTransactionVoid;

public class AccountRepositoryImpl implements AccountRepository {


    public AccountRepositoryImpl() {
    }


    @Override
    public Optional<Account> getById(Long id) {
        return doInTransactionFunc(session -> {
            Optional<Account> accountOptional = Optional.of(session.get(Account.class, id));
            return accountOptional;
        });
    }

    @Override
    public Account update(Account account) {
        return doInTransactionFunc(session -> {
            session.update(account);
            return account;
        });
    }


    @Override
    public void deleteById(Long id) {
        doInTransactionVoid(session -> {
            Account account = new Account();
            account.setId(id);
            session.delete(account);
        });
    }

    @Override
    public List<Account> getAll() {
        return doInTransactionFunc(session -> {
            List<Account> accounts = session.createQuery("FROM Account ", Account.class).list();
            return accounts;
        });
    }

    @Override
    public Account create(Account account) {
        return doInTransactionFunc(session -> {
            session.save(account);
            return account;
        });
    }

}
