package ru.zagbor.practice.suleimanov.service;

import ru.zagbor.practice.suleimanov.model.Customer;
import ru.zagbor.practice.suleimanov.model.Specialty;

import java.util.Optional;
import java.util.Set;

public interface SpecialtyService {

    Set<Specialty> getAll();

    Optional<Specialty> getById(Long id);

    void deleteById(Long id);

    Specialty create(Specialty specialty);

    boolean isSpecialtyExist(long id);

    void deleteSpecialtyCustomer(Specialty specialty, Customer customer);

    Set<Specialty> findWhichCanAdd(Set<Specialty> specialties);

}
