package com.kalatag.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kalatag.domain.Customer;
import com.kalatag.domain.Person;

@Repository
public class CustomerDaoImp extends GenericDaoImp<Customer>  implements CustomerDao{ 
	@Autowired
	private SessionFactory sessionFactory;
	

	
	@SuppressWarnings("unchecked")
	@Override
	public Customer findByUserName(String username) {
 
		List<Customer> customers = new ArrayList<Customer>();
 
		customers = sessionFactory.getCurrentSession()
			.createQuery("from Customer where username=?")
			.setParameter(0, username)
			.list();
 
		if (customers.size() > 0) {
			return (Customer) customers.get(0);
		} else {
			return null;
		}
 
	}
}
