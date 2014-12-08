package com.kalatag.dao;

import com.kalatag.domain.Person;

public interface PersonDao extends GenericDao<Person>{
	Person findByUserName(String username);
	int changePassword(int id, String newPassword );
}
