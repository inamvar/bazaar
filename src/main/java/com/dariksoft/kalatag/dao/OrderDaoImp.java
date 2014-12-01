package com.dariksoft.kalatag.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dariksoft.kalatag.domain.Deal;
import com.dariksoft.kalatag.domain.Order;

@Repository
public class OrderDaoImp extends GenericDaoImp<Order> implements OrderDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findPendingOrders(Deal deal) {

		List<Order> results = new ArrayList<Order>();
		String hql = "FROM Order O WHERE O.coupons.size = 0";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		results = query.list();
		if (results == null)
				results = new ArrayList<Order>();
		return results;
	}

}
