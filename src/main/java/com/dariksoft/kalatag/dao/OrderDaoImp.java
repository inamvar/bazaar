package com.dariksoft.kalatag.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
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

	@Override
	public Order find(int id) {
		Order order = (Order) this.sessionFactory.getCurrentSession().get(
				Order.class, id);
		Hibernate.initialize(order.getCoupons());
		return order;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findPendingOrders(Deal deal) {

		List<Order> results = new ArrayList<Order>();
		String hql = "FROM Order O WHERE O.coupons.size = 0 AND O.deal= :deal";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("deal", deal);
		results = query.list();
		if (results == null)
			results = new ArrayList<Order>();
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findOrdersByDeal(Deal deal) {
		List<Order> results = new ArrayList<Order>();
		String hql = "FROM Order O WHERE O.deal= :deal";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("deal", deal);
		results = query.list();
		if (results == null)
			results = new ArrayList<Order>();
		return results;
	}

}
