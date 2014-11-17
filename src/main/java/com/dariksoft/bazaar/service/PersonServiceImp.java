package com.dariksoft.bazaar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dariksoft.bazaar.dao.PersonDao;
import com.dariksoft.bazaar.domain.Person;

@Service
public class PersonServiceImp   implements PersonService {


	@Autowired
	PersonDao personDao;
	
	@Override
	public Person create(Person t) {
		
		return null;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Person find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person update(Person t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
