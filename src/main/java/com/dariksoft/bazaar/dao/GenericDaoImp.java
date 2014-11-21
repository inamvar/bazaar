package com.dariksoft.bazaar.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dariksoft.bazaar.controller.ProvinceController;

public abstract class GenericDaoImp<T> implements GenericDao<T> {

	@Autowired
	private SessionFactory sessionFactory;

	private static  Logger logger;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	
	}

	private Class<T> type;

	public GenericDaoImp() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];
		logger = LoggerFactory
				.getLogger(type);
	}

	@Override
	public T create(final T t) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(t);
		logger.info("create object: " + t.toString());
		return t;
	}

	@Override
	public void delete(final Object id) {
		Session session = this.sessionFactory.getCurrentSession();
		T p = (T) session.load(type, new Integer((Integer) id));
		if (null != p) {
			session.delete(p);
			logger.info("delete object: " + p.toString());
		}
	}

	@Override
	public T find(int id) {
		 Session session = null;
	        T obj = null;
	        try {
	            session = this.sessionFactory.openSession();
	            obj =(T)  session.load(type,
	                    id);
	            Hibernate.initialize(obj);
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (session != null && session.isOpen()) {
	                session.close();
	            }
	        }
	        logger.info("find object: " + obj.toString());
	        return obj;
	}

	@Override
	public T update(final T t) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(t);
		  logger.info("update object: " + t.toString());
		return t;
	}

	@Override
	public List<T> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createCriteria(type).list();
	}

}