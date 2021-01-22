package ru.zagbor.practice.suleimanov.repository.impl;

import org.hibernate.SessionFactory;
import ru.zagbor.practice.suleimanov.db.HibernateSessionFactory;
import ru.zagbor.practice.suleimanov.model.Specialty;
import ru.zagbor.practice.suleimanov.repository.SpecialtyRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static ru.zagbor.practice.suleimanov.db.TransactionService.doInTransactionFunc;
import static ru.zagbor.practice.suleimanov.db.TransactionService.doInTransactionVoid;


public class SpecialtyRepositoryImpl implements SpecialtyRepository {


    public SpecialtyRepositoryImpl() {
    }

    @Override
    public Optional<Specialty> getById(Long id) {
        return doInTransactionFunc(session -> {
            Optional<Specialty> specialtyOptional = Optional.of(session.get(Specialty.class, id));
            return specialtyOptional;
        });
    }


    @Override
    public Specialty update(Specialty specialty) {
        return doInTransactionFunc(session -> {
            session.update(specialty);
            return specialty;
        });
    }

    @Override
    public Specialty create(Specialty specialty) {
        return doInTransactionFunc(session -> {
            session.save(specialty);
            return specialty;
        });
    }


    @Override
    public void deleteById(Long id) {
        doInTransactionVoid(session -> {
            Specialty specialty = new Specialty();
            specialty.setId(id);
            session.delete(specialty);
        });
    }


    @Override
    public boolean isSpecialtyExist(long id) {
        Optional<Specialty> optionalSpecialty =
                doInTransactionFunc(session ->
                        Optional.ofNullable(session.get(Specialty.class, id)));
        return optionalSpecialty.isPresent();
    }


    @Override
    public Set<Specialty> getAll() {
        return doInTransactionFunc(session -> {
            List<Specialty> specialties = session.createQuery("FROM Specialty ", Specialty.class).list();
            return new HashSet<>(specialties);
        });
    }

}

