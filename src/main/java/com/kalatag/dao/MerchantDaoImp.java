package com.kalatag.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kalatag.domain.City;
import com.kalatag.domain.Customer;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getCustomers(Merchant merchant) {
		List<Customer> customers = new ArrayList<Customer>();
		String hql = "select O.person from Order O where O.deal in (from Deal D where D.merchant= :merchant) ";	
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("merchant", merchant);
		customers = query.list();
		return customers;
	}

	@Override
	public Map<City, Integer> getCityCount(Merchant merchant) {
		
		/*List<Customer> customers = new ArrayList<Customer>();
		String hql = "select C.contact.city, COUNT(*) from Customer  C WHERE C.Customer in(select O.Customer from Order where O.Deal in(select from Deal D where merchant= :merchant)) group by C.City";	
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("merchant", merchant);
		customers = query.list();
		return customers;*/
		return null;
	}

}
