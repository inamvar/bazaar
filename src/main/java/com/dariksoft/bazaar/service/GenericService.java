package com.dariksoft.bazaar.service;

import java.util.List;

import com.dariksoft.bazaar.domain.Country;

public interface GenericService<T> {
    T create(T t);

    void delete(int id);

    T find(int id);

    T update(T t);
    
    List<T> findAll();
}
