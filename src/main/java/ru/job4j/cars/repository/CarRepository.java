package ru.job4j.cars.repository;

import ru.job4j.cars.model.Car;

import java.util.Collection;
import java.util.Optional;

public interface CarRepository {

    Car save(Car car);

    boolean deleteById(int id);

    boolean update(Car car);

    Optional<Car> findById(int id);

    Collection<Car> findAll();
}
