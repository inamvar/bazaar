package com.offeronline.service.person;

import com.offeronline.domain.Person;
import com.offeronline.service.CRUDService;

public interface PersonService  extends CRUDService<Person>{
	Person findByUserName(String username);
	int changePassword(int id, String newPassword );
	int resetPassword(int id, String newPassword);
	
	
}
