package com.offeronline.dao;

import java.util.List;
import java.util.Map;

import com.offeronline.domain.City;
import com.offeronline.domain.Customer;
import com.offeronline.domain.Merchant;
import com.offeronline.domain.Person;

public interface MerchantDao extends GenericDao<Merchant>{

	Merchant findByPerson(Person person);
	List<Customer> getCustomers(Merchant merchant);
	Map<City,Integer> getCityCount(Merchant merchant);
}
