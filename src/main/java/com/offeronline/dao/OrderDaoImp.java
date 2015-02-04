package com.offeronline.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.offeronline.domain.Deal;
import com.offeronline.domain.Order;
import com.offeronline.domain.OrderStatus;
import com.offeronline.domain.Person;
import com.offeronline.util.Util;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findOrdersByCustomer(Person customer) {
		List<Order> results = new ArrayList<Order>();
		String hql = "FROM Order O WHERE O.person= :customer";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("customer", customer);
		results = query.list();
		if (results == null)
			results = new ArrayList<Order>();
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findOrders(Date startDate, Date endDate,
			String customerFirstName, String customerLastName,String merchantName, String dealName,OrderStatus status, int id) {

		List<Order> results = new ArrayList<Order>();
		
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Order.class,"O").createAlias("O.person", "P");
		criteria.createAlias("O.deal", "D");
		criteria.createAlias("D.merchant","M");
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	
		if (startDate != null) {
			startDate = Util.getDateWithoutTime(startDate);
			criteria.add(Restrictions.ge("O.orderDate",startDate));
		}
		if (endDate != null) {
			endDate = Util.getDateWithoutTime(Util.getTomorrowDate(endDate));
			criteria.add(Restrictions.le("O.orderDate",endDate));
		}
		
		if(customerFirstName !=null && !customerFirstName.isEmpty()){
			criteria.add(Restrictions.like("P.firstName","%"+customerFirstName+"%"));
		}
		if(customerLastName !=null && !customerLastName.isEmpty()){
			criteria.add(Restrictions.like("P.lastName","%"+customerLastName+"%"));
		}
	
		
		if(merchantName !=null && !merchantName.isEmpty()){
			criteria.add(Restrictions.like("M.name","%"+merchantName+"%"));	
		}
			
		if(dealName !=null && !dealName.isEmpty()){
			criteria.add(Restrictions.like("D.name","%"+dealName+"%"));
		}
		
		if(status !=null){
			criteria.add(Restrictions.eq("O.status",status));
		}
		
		if(id >0){
			criteria.add(Restrictions.eq("O.id",id));
		}
		
		criteria.addOrder( org.hibernate.criterion.Order.desc("O.id"));
		results =criteria.list();
		
		if (results == null)
			results = new ArrayList<Order>();
		return results;
	}

	@Override
	public int updateStatus(List<Order> orders, OrderStatus status) {
		
		String hql = "UPDATE Order O  SET O.status = :status WHERE O IN (:orders)";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameterList("orders", orders);
		query.setParameter("status", status);
		int ret = query.executeUpdate();
	
		return ret;
	}

}
