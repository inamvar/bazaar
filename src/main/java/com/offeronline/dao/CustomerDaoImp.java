package com.offeronline.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.offeronline.domain.Customer;

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
