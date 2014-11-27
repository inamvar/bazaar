package com.dariksoft.kalatag.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dariksoft.kalatag.domain.PersonRole;

@Repository
public class PersonRoleDaoImp extends GenericDaoImp<PersonRole> implements PersonRoleDao{

	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public PersonRole findByRoleName(String roleName) {
 
		List<PersonRole> roles = new ArrayList<PersonRole>();
 
		roles = sessionFactory.getCurrentSession()
			.createQuery("from PersonRole where role=?")
			.setParameter(0, roleName)
			.list();
 
		if (roles.size() > 0) {
			return roles.get(0);
		} else {
			return null;
		}
 
	}
}
