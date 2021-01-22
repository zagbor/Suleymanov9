package ru.zagbor.practice.suleimanov.repository;

import ru.zagbor.practice.suleimanov.model.Customer;
import ru.zagbor.practice.suleimanov.model.Specialty;

import java.util.Set;

public interface SpecialtyRepository extends GenericRepository<Specialty, Long> {




    boolean isSpecialtyExist(long id);

    Set<Specialty> getAll();


}
