package com.hometask.servlets.repository;

import java.util.List;

public interface GenericRepository<T, ID>{
    T getById(ID id);

    boolean deleteById(ID id);

    List<T> getAll();

    T save(T obj);

    T update(T obj);
}
