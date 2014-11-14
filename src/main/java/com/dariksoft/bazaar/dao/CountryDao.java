package com.dariksoft.bazaar.dao;

import java.util.List;

import com.dariksoft.bazaar.domain.Country;

public interface CountryDao {
	void insert(Country country);
	int delete(Country country);
	int delete(int id);
	int update(Country country);
	Country findbyId(int id);
	List<Country> findAll();
}
