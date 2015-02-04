package com.offeronline.service;

import com.offeronline.domain.Customer;

public interface CustomerService extends CRUDService<Customer> {
	Customer findByUserName(String username);
}
