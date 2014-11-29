package com.dariksoft.kalatag.dao;

import com.dariksoft.kalatag.domain.Person;

public interface PersonDao extends GenericDao<Person>{
	Person findByUserName(String username);
	int changePassword(int id, String newPassword );
}
