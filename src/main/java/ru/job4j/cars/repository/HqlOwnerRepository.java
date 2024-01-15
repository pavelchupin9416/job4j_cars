package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Owner;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HqlOwnerRepository implements OwnerRepository {
    private CrudRepository crudRepository;

    public Owner save(Owner owner) {
        crudRepository.run(session -> session.save(owner));
        return owner;
    }

    @Override
    public boolean deleteById(int id) {
        return crudRepository.run("DELETE FROM Owner WHERE id = :id",
                Map.of("id", id));
    }

    public boolean update(Owner owner) {
        return crudRepository.run("UPDATE Owner SET name = :fName WHERE id = :fId",
                Map.of("fName", owner.getName(),
                        "fId", owner.getId()));
    }

    public List<Owner> findAll() {
        return crudRepository.query("FROM Owner ORDER BY id", Owner.class);
    }

    public Optional<Owner> findById(int id) {
        return crudRepository.optional("FROM Owner WHERE id = :id", Owner.class, Map.of("id", id));
    }
}
