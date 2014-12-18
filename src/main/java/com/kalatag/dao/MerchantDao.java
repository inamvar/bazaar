package com.kalatag.dao;

import java.util.List;
import java.util.Map;

import com.kalatag.domain.City;
import com.kalatag.domain.Customer;
import com.kalatag.domain.Merchant;
import com.kalatag.domain.Person;

public interface MerchantDao extends GenericDao<Merchant>{

	Merchant findByPerson(Person person);
	List<Customer> getCustomers(Merchant merchant);
	Map<City,Integer> getCityCount(Merchant merchant);
}
