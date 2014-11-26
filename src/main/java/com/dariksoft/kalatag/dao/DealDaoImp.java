package com.dariksoft.kalatag.dao;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dariksoft.kalatag.domain.Deal;

@Repository
public class DealDaoImp extends GenericDaoImp<Deal> implements DealDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public Deal find(int id) {
		
		Deal deal =(Deal) this.sessionFactory.getCurrentSession().get(Deal.class, id);
		Hibernate.initialize(deal.getOptions());
		
		return deal;

	}
	
}
