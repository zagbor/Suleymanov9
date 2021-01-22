package ru.zagbor.practice.suleimanov.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.zagbor.practice.suleimanov.db.HibernateSessionFactory;
import ru.zagbor.practice.suleimanov.model.Customer;
import ru.zagbor.practice.suleimanov.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import static ru.zagbor.practice.suleimanov.db.TransactionService.doInTransactionFunc;
import static ru.zagbor.practice.suleimanov.db.TransactionService.doInTransactionVoid;


public class CustomerRepositoryImpl implements CustomerRepository {


    @Override
    public Customer update(Customer customer) {
        return doInTransactionFunc(session -> {
            session.update(customer);
            return customer;
        });
    }

    @Override
    public List<Customer> getAll() {
        return doInTransactionFunc(session -> {
            List<Customer> customers = session.createQuery("FROM Customer", Customer.class).list();
            return customers;
        });
    }

    @Override
    public Optional<Customer> getById(Long id) {
        return doInTransactionFunc(session -> {
            Optional<Customer> customerOptional = Optional.of(session.get(Customer.class, id));
            return customerOptional;
        });
    }

    public Customer create(Customer customer) {
        return doInTransactionFunc(session -> {
            session.save(customer);
            return customer;
        });
    }

    @Override
    public void deleteById(Long id) {
        doInTransactionVoid(session -> {
            Customer customer = new Customer();
            customer.setId(id);
            session.delete(customer);
        });
    }

    public boolean isCustomerExist(long id) {
        Optional<Customer> optionalCustomer =
                doInTransactionFunc(session ->
                        Optional.ofNullable(session.get(Customer.class, id)));
        return optionalCustomer.isPresent();
    }




}