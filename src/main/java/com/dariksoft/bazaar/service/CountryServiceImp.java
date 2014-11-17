package com.dariksoft.bazaar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dariksoft.bazaar.dao.CountryDao;
import com.dariksoft.bazaar.domain.Country;

@Service
public class CountryServiceImp   implements CountryService{

	@Autowired
	CountryDao countryDao;

	@Override
	public Country create(Country t) {
		return countryDao.create(t);
	}

	@Override
	public void delete(int id) {
		countryDao.delete(id);
		
	}

	@Override
	public Country find(int id) {
	return countryDao.find(id);
	}

	@Override
	public Country update(Country t) {
	return countryDao.update(t);
	}

	@Override
	public List<Country> findAll() {
		return countryDao.findAll();
	}

}
