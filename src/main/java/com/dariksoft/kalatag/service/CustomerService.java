package com.dariksoft.kalatag.service;

import com.dariksoft.kalatag.domain.Customer;

public interface CustomerService extends CRUDService<Customer> {
	Customer findByUserName(String username);
}
