package com.dariksoft.bazaar.dao;

import java.util.List;

import com.dariksoft.bazaar.domain.Person;

public interface PersonDao {
	void insert(Person person);
	int delete(Person person);
	int delete(int id);
	int update(Person person);
	Person findbyId(int id);
	List<Person> findAll();
}
