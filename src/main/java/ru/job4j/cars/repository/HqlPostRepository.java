package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Brand;
import ru.job4j.cars.model.Post;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HqlPostRepository implements PostRepository {

    CrudRepository crudRepository;

    @Override
    public Post save(Post post) {
        return crudRepository.run(session -> session.save(post)) ? post : null;
    }

    @Override
    public boolean deleteById(int id) {
        return crudRepository.run(
                "delete Post as t where t.id = :id", Map.of("id", id));
    }

    @Override
    public boolean update(Post post) {
        return crudRepository.run(session -> session.update(post));
    }

    @Override
    public Optional<Post> findById(int id) {
        return crudRepository.optional("select distinct p from Post p JOIN FETCH p.car JOIN FETCH p.priceHistory "
                        + "JOIN FETCH p.file JOIN FETCH p.user where p.id = :id",
                Post.class, Map.of("id", id));
    }

    @Override
    public Collection<Post> findAll() {
        return crudRepository.query("select distinct p from Post p JOIN FETCH p.car JOIN FETCH p.priceHistory "
                        + "JOIN FETCH p.file JOIN FETCH p.user",
                Post.class);
    }

    @Override
    public Collection<Post> findByLastDay() {
        LocalDateTime date = LocalDateTime.now().minus(1, ChronoUnit.DAYS);
        return crudRepository.query("select distinct p from Post p JOIN FETCH p.car JOIN FETCH p.priceHistory "
                        + "JOIN FETCH p.file JOIN FETCH p.user "
                        + " where p.created >= :date",
                Post.class, Map.of("date", date));
    }

    @Override
    public Collection<Post> findWithPhoto() {
        return crudRepository.query("select distinct p from Post p JOIN FETCH p.car JOIN FETCH p.priceHistory "
                + "JOIN FETCH p.file JOIN FETCH p.user"
                + "where p.file is not null", Post.class);
    }

    @Override
    public Collection<Post> findByBrand(String brand) {
        return crudRepository.query("select distinct p from Post p JOIN FETCH p.car JOIN FETCH p.priceHistory "
                        + "JOIN FETCH p.file JOIN FETCH p.user"
                        + "where p.brand.name = :brand",
                Post.class, Map.of("brand", brand));
    }
}
