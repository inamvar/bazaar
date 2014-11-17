package com.dariksoft.bazaar.service;

import java.util.List;

public interface CRUDService<T> {
    T create(T t);

    void delete(int id);

    T find(int id);

    T update(T t);
    
    List<T> findAll();
}
