package com.dariksoft.kalatag.service.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dariksoft.kalatag.dao.PersonDao;
import com.dariksoft.kalatag.domain.Person;
import com.dariksoft.kalatag.service.CRUDServiceImp;
import com.dariksoft.kalatag.util.Util;

@Service
public class PersonServiceImp  extends CRUDServiceImp<Person> implements PersonService {

	@Autowired
	PersonDao personDao;
	
	@Override
	@Transactional
	public Person findByUserName(String username) {	
		return personDao.findByUserName(username);
	}

	@Override
	@Transactional
	public int changePassword(int id, String newPassword) {
		return personDao.changePassword(id, newPassword);
	}

	@Override
	@Transactional
	public int resetPassword(int id) {
		String newPassword = Util.generateRandomPassword();
		return personDao.changePassword(id, newPassword);
	}
	

	
	
	
}
