package ru.zagbor.practice.suleimanov.repository;


import ru.zagbor.practice.suleimanov.model.Customer;
import ru.zagbor.practice.suleimanov.model.Specialty;

import java.util.List;

public interface CustomerRepository extends GenericRepository<Customer, Long> {

    List<Customer> getAll();


    boolean isCustomerExist(long id);

}
