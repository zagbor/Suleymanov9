package ru.zagbor.practice.suleimanov.controller;


import ru.zagbor.practice.suleimanov.model.Customer;
import ru.zagbor.practice.suleimanov.model.Specialty;
import ru.zagbor.practice.suleimanov.service.SpecialtyService;
import ru.zagbor.practice.suleimanov.service.impl.SpecialtyServiceImpl;

import java.util.Optional;
import java.util.Set;

public class SpecialtyController {

    private final SpecialtyService specialtyService;

    public SpecialtyController() {
        specialtyService = new SpecialtyServiceImpl();
    }


    public Optional<Specialty> getSpecialtyForId(long id)  {
        return specialtyService.getById(id);
    }


    public Set<Specialty> specialtiesWhichCanAdd(Set<Specialty> specialties) {
        return specialtyService.findWhichCanAdd(specialties);
    }


    public boolean isSpecialtyExist(long id) {
        return specialtyService.isSpecialtyExist(id);
    }


    public Specialty create(Specialty specialty){
        return specialtyService.create(specialty);
    }


    public Set<Specialty> getAll(){
        return specialtyService.getAll();
    }


    public void deleteById(Long id) {
        specialtyService.deleteById(id);
    }


    public void deleteSpecialtyCustomer(Specialty specialty, Customer customer) {
        specialtyService.deleteSpecialtyCustomer(specialty, customer);
    }

}