package com.kalatag.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kalatag.domain.Deal;
import com.kalatag.domain.DealLabel;
import com.kalatag.domain.ItemCategory;
import com.kalatag.domain.ItemStatus;
import com.kalatag.domain.Merchant;
import com.kalatag.domain.OrderStatus;

@Repository
public class DealDaoImp extends GenericDaoImp<Deal> implements DealDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Deal find(int id) {

		Deal deal = (Deal) this.sessionFactory.getCurrentSession().get(
				Deal.class, id);
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
	public List<Deal> findDealsByLabelAndStatus(DealLabel label,
			ItemStatus status) {
		List<Deal> results = new ArrayList<Deal>();
		String hql = "FROM Deal D WHERE D.status = :status AND D.label= :label order by D.id DESC";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("status", status);
		query.setParameter("label", label);
		results = query.list();
		for (Deal deal : results) {

			Hibernate.initialize(deal.getImages());
		}

		return results;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Deal> findDealsByStatusAndNotLabel(DealLabel label,
			ItemStatus status) {
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
	public List<Deal> findDealsByCategoryAndStatusAndNotLabel(
			ItemCategory category, DealLabel label, ItemStatus status) {

		List<Deal> results = new ArrayList<Deal>();
		String hql = "FROM Deal D WHERE D.status = :status AND D.label <> :label  AND category= :category order by D.id DESC";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("status", status);
		query.setParameter("label", label);
		query.setParameter("category", category);
		results = query.list();

		return results;
	}

	@Override
	public int getSold(Deal deal) {
		String hql = "select sum(O.quantity) from Order O WHERE O.deal = :deal AND O.status <> :status";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("deal", deal);
		query.setParameter("status", OrderStatus.CANCELED);
		java.lang.Long l = (java.lang.Long) query.uniqueResult();
		int result = l != null ? l.intValue() : 0;
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Deal> findDealsByMerchant(Merchant merchant) {
		List<Deal> results = new ArrayList<Deal>();
		String hql = "from Deal D WHERE D.merchant = :merchant";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("merchant", merchant);
		results = query.list();

		return results;

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Deal> findSimilars(Deal deal) {
		List<Deal> results = new ArrayList<Deal>();
		String hql = "from Deal D WHERE D.category = :category and D.id != :id and D.status= :status order by D.id DESC";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("category", deal.getCategory());
		query.setParameter("id", deal.getId());
		query.setParameter("status", ItemStatus.ON);
		results = query.list();

		return results;
	}

}
