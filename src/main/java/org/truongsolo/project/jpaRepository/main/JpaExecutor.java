package org.truongsolo.project.jpaRepository.main;

import org.truongsolo.project.entity.Student;

import java.util.List;
import java.util.Optional;

public interface JpaExecutor <T> {
    List<T> findAll();
    Optional<T> findById(Number id);
    void add(T entity);
    void update(T entity);
    void delete(T entity);
}
