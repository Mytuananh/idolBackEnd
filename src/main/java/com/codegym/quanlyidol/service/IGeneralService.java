package com.codegym.quanlyidol.service;

import java.util.Optional;

public interface IGeneralService<T> {
    T save(T t);

    Iterable<T> findAll();

    Optional<T>  findById(Long id);

    void delete(Long id);
}
