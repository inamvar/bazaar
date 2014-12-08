package com.kalatag.service.person;

import com.kalatag.domain.Person;
import com.kalatag.service.CRUDService;

public interface PersonService  extends CRUDService<Person>{
	Person findByUserName(String username);
	int changePassword(int id, String newPassword );
	int resetPassword(int id, String newPassword);
	
	
}
