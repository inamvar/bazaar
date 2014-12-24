package com.kalatag.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kalatag.domain.Merchant;
import com.kalatag.domain.MerchantPayment;
import com.kalatag.domain.OrderStatus;

@Repository
public class MerchantPaymentDaoImp extends GenericDaoImp<MerchantPayment>
		implements MerchantPaymentDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public double GetBalance(Merchant merchant) {

		double totalSold = this.getTotalSold(merchant);
		double totalPaid = this.GetTotalPaid(merchant);
		return totalSold - totalPaid;

	}

	@Override
	public double GetTotalPaid(Merchant merchant) {
		String hql = "SELECT SUM(M.amount) FROM  MerchantPayment M WHERE M.merchant = :merchant";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("merchant", merchant);
		Double d = (Double) query.uniqueResult();
		double totalPaid = d == null ? 0 : d;
		return totalPaid;
	}

	@Override
	public double getTotalSold(Merchant merchant) {
		String hql = "SELECT SUM(O.totalPrice) FROM  Order O WHERE O.status = :status and O.deal in (from Deal D where D.merchant = :merchant)";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("merchant", merchant);
		query.setParameter("status", OrderStatus.DONE);
		Double d = (Double) query.uniqueResult();
		double totalSold = d ==null ? 0 : d;		
		return totalSold;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MerchantPayment> findByMerchant(Merchant merchant) {
		List<MerchantPayment> results = new ArrayList<MerchantPayment>();
		String hql = "FROM  MerchantPayment M WHERE M.merchant = :merchant order by M.paymentDate DESC";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("merchant", merchant);
		results = query.list();
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MerchantPayment> findByMerchant(Merchant merchant,
			Date fromDate, Date toDate) {
		List<MerchantPayment> results = new ArrayList<MerchantPayment>();
		String hql = "FROM  MerchantPayment M WHERE M.merchant = :merchant and paymentDate between :fromDate and :toDate order by M.paymentDate DESC";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("merchant", merchant);
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		results = query.list();
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MerchantPayment> findByMerchant(Merchant merchant,
			int maximumRowResult) {
		List<MerchantPayment> results = new ArrayList<MerchantPayment>();
		String hql = "FROM  MerchantPayment M WHERE M.merchant = :merchant order by M.paymentDate DESC";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("merchant", merchant);
		query.setMaxResults(maximumRowResult);
		results = query.list();
		return results;
	}

}
