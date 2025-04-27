package org.example.miniprojetback.Services;

import java.util.List;
import java.util.Optional;

public interface CrudService<T> {
    T create(T entity);
    Optional<T> getById(Long id);
    List<T> getAll();
    T update(Long id, T entity);
    void delete(Long id);
}