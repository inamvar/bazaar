package com.dariksoft.kalatag.service;

import java.util.List;

public interface CRUDService<T> {
    T create(T t);

    void delete(int id);

    T find(int id);

    T update(T t);

    Integer save(T t);
    
    List<T> findAll();
}
