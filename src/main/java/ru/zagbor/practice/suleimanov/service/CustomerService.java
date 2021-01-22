package ru.zagbor.practice.suleimanov.service;

import ru.zagbor.practice.suleimanov.model.Customer;
import ru.zagbor.practice.suleimanov.model.Specialty;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Customer update(Customer customer);

    List<Customer> getAll();

    Optional<Customer> getCustomerById(Long id);

    void deleteById(Long id);

    Customer create(Customer customer);

    void deleteSpecialtyCustomer(Customer customer, Specialty specialty);

    void changeName(Customer customer, String name);

    boolean isCustomerExist(long id);

    void addSpecialtyCustomer(Customer customer, Long specialtyId);


}
