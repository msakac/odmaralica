package org.foi.diplomski.msakac.odmaralica.utils;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;

public class GenericRepository {

    public static <T, R extends JpaRepository<T, ?>> T findOneByExample(T example, R repository) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<T> exampleEntity = Example.of(example, matcher);

        List<T> matchingEntities = repository.findAll(exampleEntity);
        if (!matchingEntities.isEmpty()) {
            return matchingEntities.get(0);
        } else {
            return null;
        }
    }

    public static <T, R extends JpaRepository<T, ?>> List<T> findAllByExample(T example, R repository) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<T> exampleEntity = Example.of(example, matcher);

        return repository.findAll(exampleEntity);
    }
}
