package com.offeronline.dao;

import com.offeronline.domain.Person;

public interface PersonDao extends GenericDao<Person>{
	Person findByUserName(String username);
	int changePassword(int id, String newPassword );
}
