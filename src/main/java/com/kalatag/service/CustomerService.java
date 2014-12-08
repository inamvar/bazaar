package com.kalatag.service;

import com.kalatag.domain.Customer;

public interface CustomerService extends CRUDService<Customer> {
	Customer findByUserName(String username);
}
