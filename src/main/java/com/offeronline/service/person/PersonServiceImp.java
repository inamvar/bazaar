package com.offeronline.service.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.offeronline.dao.PersonDao;
import com.offeronline.domain.Person;
import com.offeronline.service.CRUDServiceImp;
import com.offeronline.util.Util;

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
	public int resetPassword(int id, String newPassword) {
		
		return personDao.changePassword(id, newPassword);
	}
	

	
	
	
}
