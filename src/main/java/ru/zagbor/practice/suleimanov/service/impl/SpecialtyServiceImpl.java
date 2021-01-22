package ru.zagbor.practice.suleimanov.service.impl;

import ru.zagbor.practice.suleimanov.model.Customer;
import ru.zagbor.practice.suleimanov.model.Specialty;
import ru.zagbor.practice.suleimanov.repository.SpecialtyRepository;
import ru.zagbor.practice.suleimanov.repository.impl.SpecialtyRepositoryImpl;
import ru.zagbor.practice.suleimanov.service.SpecialtyService;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class SpecialtyServiceImpl implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    public SpecialtyServiceImpl(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }


    public SpecialtyServiceImpl() {
        this(new SpecialtyRepositoryImpl());
    }


    @Override
    public Set<Specialty> getAll() {
        return specialtyRepository.getAll();
    }

    @Override
    public Optional<Specialty> getById(Long id) {
        return specialtyRepository.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        specialtyRepository.deleteById(id);
    }

    @Override
    public Specialty create(Specialty specialty) {
        return specialtyRepository.create(specialty);
    }


    @Override
    public boolean isSpecialtyExist(long id) {
        return specialtyRepository.isSpecialtyExist(id);
    }


    @Override
    public Set<Specialty> findWhichCanAdd(Set<Specialty> specialties) {
        Set<Specialty> allSpecialties = specialtyRepository.getAll();
        return allSpecialties.stream().filter(specialty -> !specialties.contains(specialty)).collect(Collectors.toSet());
    }

    @Override
    public void deleteSpecialtyCustomer(Specialty specialty, Customer customer) {
        customer.getSpecialties().remove(specialty);
        specialtyRepository.update(specialty);
    }


}
