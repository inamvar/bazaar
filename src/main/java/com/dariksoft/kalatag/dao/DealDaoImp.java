package com.dariksoft.kalatag.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dariksoft.kalatag.domain.Deal;
import com.dariksoft.kalatag.domain.DealLabel;
import com.dariksoft.kalatag.domain.ItemCategory;
import com.dariksoft.kalatag.domain.ItemStatus;

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
		String hql = "FROM  Deal D WHERE D.status = :status order by D.id DESC";	
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("status", status);
	     results = query.list();
	     
		return results;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Deal> findDealsByLabelAndStatus(DealLabel label, ItemStatus status){
		List<Deal> results = new ArrayList<Deal>();
		String hql = "FROM Deal D WHERE D.status = :status AND D.label= :label order by D.id DESC";	
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("status", status);
		query.setParameter("label", label);
	     results = query.list();
	     for(Deal deal : results){
	    	 
	    	 Hibernate.initialize(deal.getImages());
	     }
	     
		return results;
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Deal> findDealsByStatusAndNotLabel(DealLabel label, ItemStatus status){
		List<Deal> results = new ArrayList<Deal>();
		String hql = "FROM Deal D WHERE D.status = :status AND D.label <> :label order by D.id DESC";	
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("status", status);
		query.setParameter("label", label);
	     results = query.list();
	     
		return results;
		
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<Deal> findDealsByCategoryAndStatusAndNotLabel(ItemCategory category,
			DealLabel label, ItemStatus status) {
		
		List<Deal> results = new ArrayList<Deal>();
		String hql = "FROM Deal D WHERE D.status = :status AND D.label <> :label  AND category= :category order by D.id DESC";	
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("status", status);
		query.setParameter("label", label);
		query.setParameter("category", category);
	     results = query.list();
	     
	 	return results;
	}
	

	
}
