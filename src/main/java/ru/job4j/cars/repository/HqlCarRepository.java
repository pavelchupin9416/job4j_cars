package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HqlCarRepository implements CarRepository {

    private CrudRepository crudRepository;

    @Override
    public Car save(Car car) {
        crudRepository.run(session -> session.save(car));
        return car;
    }

    @Override
    public boolean deleteById(int id) {
        return crudRepository.run(
                "DELETE Car  WHERE id = :fId", Map.of("fId", id));
    }

    @Override
    public boolean update(Car car) {
        return crudRepository.run("UPDATE Car SET name = :fName, engine = :fEngine, owners = :fOwners WHERE id = :fId",
                Map.of("fName", car.getName(),
                        "fEngine", car.getEngine(),
                        "fId", car.getId()));
    }

    @Override
    public Optional<Car> findById(int id) {
        return crudRepository.optional("SELECT DISTINCT c FROM Car c LEFT JOIN FETCH c.owners WHERE c.id = :id",
                Car.class, Map.of("id", id));
    }

    @Override
    public Collection<Car> findAll() {
        return crudRepository.query("SELECT DISTINCT c FROM Car c LEFT JOIN FETCH c.owners ORDER BY c.id",
                Car.class);
    }
}
