package com.dariksoft.kalatag.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dariksoft.kalatag.dao.CustomerDao;
import com.dariksoft.kalatag.domain.Customer;

@Service
public class CustomerServiceImp extends CRUDServiceImp<Customer> implements CustomerService{

	@Autowired
	CustomerDao CustomerDao;
	
	@Override
	public Customer findByUserName(String username) {	
		return CustomerDao.findByUserName(username);
	}
}
