package com.kalatag.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kalatag.domain.Merchant;
import com.kalatag.domain.Person;

@Repository
public class MerchantDaoImp extends GenericDaoImp<Merchant> implements MerchantDao{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Merchant findByPerson(Person person) {
		String hql = "from Merchant M WHERE M.contactPoint = :person";	
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("person", person);
		return (Merchant) query.uniqueResult();
	}

}
