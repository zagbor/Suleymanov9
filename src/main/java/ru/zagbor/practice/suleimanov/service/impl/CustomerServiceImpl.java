package ru.zagbor.practice.suleimanov.service.impl;

import ru.zagbor.practice.suleimanov.model.Customer;
import ru.zagbor.practice.suleimanov.model.Specialty;
import ru.zagbor.practice.suleimanov.repository.CustomerRepository;
import ru.zagbor.practice.suleimanov.repository.SpecialtyRepository;
import ru.zagbor.practice.suleimanov.repository.impl.CustomerRepositoryImpl;
import ru.zagbor.practice.suleimanov.repository.impl.SpecialtyRepositoryImpl;
import ru.zagbor.practice.suleimanov.service.CustomerService;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final SpecialtyRepository specialtyRepository;

    public CustomerServiceImpl() {
        this(new CustomerRepositoryImpl(), new SpecialtyRepositoryImpl());
    }

    public CustomerServiceImpl(CustomerRepository customerRepository, SpecialtyRepository specialtyRepository) {
        this.customerRepository = customerRepository;
        this.specialtyRepository = specialtyRepository;

    }


    @Override
    public Customer update(Customer customer) {
        customerRepository.update(customer);
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = customerRepository.getAll();
        return customers;
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        Customer customer = null;
        if (customerRepository.isCustomerExist(id)) {
            customer = customerRepository.getById(id).orElseThrow(() -> new RuntimeException(format("Customer [%s] not exists", id)));
        }
        return Optional.of(customer);
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer create(Customer customer) {
        customerRepository.create(customer);
        return customer;
    }

    @Override
    public void deleteSpecialtyCustomer(Customer customer, Specialty specialty) {
        customer.getSpecialties().remove(specialty);
        customerRepository.update(customer);
    }

    @Override
    public void changeName(Customer customer, String name) {
        customer.setName(name);
        customerRepository.update(customer);
    }


    @Override
    public boolean isCustomerExist(long id) {
        return customerRepository.isCustomerExist(id);
    }

    @Override
    public void addSpecialtyCustomer(Customer customer, Long specialtyId) {
        Specialty specialty = specialtyRepository.getById(specialtyId).orElseThrow(IllegalStateException::new);
        customer.getSpecialties().add(specialty);
        customerRepository.update(customer);
    }


}
