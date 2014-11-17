package com.dariksoft.bazaar.service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.dariksoft.bazaar.dao.GenericDao;
import com.dariksoft.bazaar.domain.Country;

public class GenericServiceImp<T> implements CRUDService< T >{
	
	
	private Class< T > type;
	
	@Autowired
	GenericDao<T> genericDao;

    public GenericServiceImp() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

	@Override
	@Transactional
	public T create(T t) {
		return  genericDao.create(t);
	
	}

	@Override
	@Transactional
	public void delete(int id) {
		genericDao.delete(id);
		
	}

	@Override
	@Transactional
	public T find(int id) {
	 return genericDao.find(id);
	}

	@Override
	@Transactional
	public T update(T t) {
		return genericDao.update(t);
	}

	@Override
	@Transactional
	public List<T> findAll() {
		return genericDao.findAll();
	}

}
