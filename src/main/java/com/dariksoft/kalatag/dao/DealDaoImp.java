package com.dariksoft.kalatag.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dariksoft.kalatag.domain.Deal;
import com.dariksoft.kalatag.domain.ItemStatus;
import com.dariksoft.kalatag.domain.Person;

@Repository
public class DealDaoImp extends GenericDaoImp<Deal> implements DealDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public Deal find(int id) {
		
		Deal deal =(Deal) this.sessionFactory.getCurrentSession().get(Deal.class, id);
		Hibernate.initialize(deal.getImages());
		
		return deal;

	}
	
	


	@SuppressWarnings("unchecked")
	@Override
	public List<Deal> findDealsByStatus(ItemStatus status) {
		List<Deal> results = new ArrayList<Deal>();
		String hql = "FROM  Deal D WHERE D.status = :status ";	
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("status", status);
	     results = query.list();
	     
		return results;
	}
	
}
