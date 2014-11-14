package com.dariksoft.bazaar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dariksoft.bazaar.dao.CountryDao;
import com.dariksoft.bazaar.domain.Country;

@Service
public class CountryServiceImp implements CountryService{


	@Autowired
	CountryDao countryDao;
	
	@Override
	@Transactional
	public void insert(Country country) {
		countryDao.insert(country);
		
	}

	@Override
	@Transactional
	public int delete(Country country) {
		
		return  countryDao.delete(country);
	}

	@Override
	@Transactional
	public int delete(int id) {
		return countryDao.delete(id);
	}

	@Override
	@Transactional
	public int update(Country country) {
		return countryDao.update(country);
	}

	@Override
	@Transactional
	public List<Country> findAll() {
		return countryDao.findAll();
	}

	@Override
	@Transactional
	public Country findbyId(int id) {
		return countryDao.findbyId(id);
	}

}
