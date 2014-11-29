package com.dariksoft.kalatag.service.person;

import com.dariksoft.kalatag.domain.Person;
import com.dariksoft.kalatag.service.CRUDService;

public interface PersonService  extends CRUDService<Person>{
	Person findByUserName(String username);
	int changePassword(int id, String newPassword );
	int resetPassword(int id);
	
	
}
