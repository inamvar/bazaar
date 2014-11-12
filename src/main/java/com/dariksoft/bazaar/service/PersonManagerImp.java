package com.dariksoft.bazaar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dariksoft.bazaar.dao.PersonDao;
import com.dariksoft.bazaar.domain.Person;

@Service
public class PersonManagerImp implements PersonManager {

	@Autowired
	PersonDao personDao;

	@Transactional
	public void insert(Person person) {
		personDao.insert(person);
	}

	@Transactional
	public List<Person> findAll() {
		return personDao.findAll();
	}

	@Transactional
	public int delete(Person person) {
		return personDao.delete(person);
	}
	
	@Transactional
	public int delete(int id) {
		return personDao.delete(id);
	}
	
	
	@Transactional
	public int update(Person person){
		return personDao.update(person);
	}
	
	@Transactional
	public Person findbyId(int id) {
		return personDao.findbyId(id);
	}
	

}
