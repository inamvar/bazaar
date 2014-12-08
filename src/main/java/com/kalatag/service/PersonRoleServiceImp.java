package com.kalatag.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kalatag.dao.PersonRoleDao;
import com.kalatag.domain.PersonRole;

@Service
public class PersonRoleServiceImp extends CRUDServiceImp<PersonRole> implements PersonRoleService{

	@Autowired
	PersonRoleDao personRoleDao;
	
	@Override
	public PersonRole findByRoleName(String roleName) {	
		return personRoleDao.findByRoleName(roleName);
	}
	
}
