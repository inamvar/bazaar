package com.dariksoft.kalatag.dao;

import com.dariksoft.kalatag.domain.Customer;

public interface CustomerDao extends GenericDao<Customer> {
	Customer findByUserName(String username);
}
