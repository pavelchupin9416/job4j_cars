package ru.job4j.cars.repository;

import ru.job4j.cars.model.Brand;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.model.Post;

import java.util.Collection;
import java.util.Optional;

public interface PostRepository {
    Post save(Post post);

    boolean deleteById(int id);

    boolean update(Post post);

    Optional<Post> findById(int id);

    Collection<Post> findAll();

    Collection<Post> findByLastDay();

    Collection<Post> findWithPhoto();

    Collection<Post> findByBrand(Brand brand);

}
