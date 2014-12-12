package com.kalatag.dao;

import com.kalatag.domain.Customer;

public interface CustomerDao extends GenericDao<Customer> {
	Customer findByUserName(String username);

}
