package com.dariksoft.bazaar.service;

import java.util.List;

import com.dariksoft.bazaar.domain.Country;

public interface CountryService {
	void insert(Country country);
	int delete(Country country);
	int delete(int id);
	int update(Country country);
	List<Country> findAll();
	Country findbyId(int id);
}
