package com.dariksoft.kalatag.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dariksoft.kalatag.domain.Person;
import com.dariksoft.kalatag.util.Util;

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

	@Override
	public int changePassword(int id, String newPassword) {
		String hql = "UPDATE Person set password = :password "  + 
	             "WHERE id = :person_id";
	Query query = sessionFactory.getCurrentSession().createQuery(hql);
	query.setParameter("password", Util.toSHA256(newPassword));
	query.setParameter("person_id", id);
	int result = query.executeUpdate();
		return result;
	}
	
}
