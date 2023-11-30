package com.uadybank.uadybankbackend.service;

import java.util.List;

public interface iService<T> {

    List<T> getAll();

    T getById(Long id);

    T save(T entity);

    T update(Long id, T entity);

    void delete(Long id);

}
