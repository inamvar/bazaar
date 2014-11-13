package com.dariksoft.bazaar.service;

import java.util.List;

import com.dariksoft.bazaar.domain.Person;

public interface PersonService {
	void insert(Person person);
	int delete(Person person);
	int delete(int id);
	int update(Person person);
	List<Person> findAll();
	Person findbyId(int id);

}
