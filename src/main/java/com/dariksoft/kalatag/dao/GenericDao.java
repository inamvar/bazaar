package com.dariksoft.kalatag.dao;

import java.util.List;

public interface GenericDao< T > {


    T create(T t);

    void delete(Object id);

    T find(int id);

    T update(T t);
    
    List<T> findAll();
}
