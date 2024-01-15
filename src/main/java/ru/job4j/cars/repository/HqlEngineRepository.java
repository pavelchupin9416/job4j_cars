package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Engine;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HqlEngineRepository {

    private CrudRepository crudRepository;

    public Engine save(Engine engine) {
        crudRepository.run(session -> session.save(engine));
        return engine;
    }

    public boolean update(Engine engine) {
        return crudRepository.run("UPDATE Engine SET name = :fName WHERE id = :fId",
                Map.of("fName", engine.getName(),
                        "fId", engine.getId()));
    }

    public boolean delete(int id) {
        return crudRepository.run("DELETE FROM Engine WHERE id = :id",
                Map.of("id", id));
    }

    public List<Engine> findAll() {
        return crudRepository.query("FROM Engine ORDER BY id", Engine.class);
    }

    public Optional<Engine> findById(int id) {
        return crudRepository.optional("FROM Engine WHERE id = :id", Engine.class, Map.of("id", id));
    }
}
