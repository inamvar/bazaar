package com.offeronline.dao;

import com.offeronline.domain.Customer;

public interface CustomerDao extends GenericDao<Customer> {
	Customer findByUserName(String username);
}
