package com.dariksoft.kalatag.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dariksoft.kalatag.domain.Person;

@Repository
public class PersonDaoImp  extends GenericDaoImp<Person> implements PersonDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public Person findByUserName(String username) {
 
		List<Person> users = new ArrayList<Person>();
 
		users = sessionFactory.getCurrentSession()
			.createQuery("from Person where username=?")
			.setParameter(0, username)
			.list();
 
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
 
	}
	
}
