package com.kalatag.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kalatag.domain.Account;

@Repository
public class AccountDaoImp extends GenericDaoImp<Account> implements AccountDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void debit(int id, double amount) {
		updateBalance(id, -amount);

	}

	@Override
	public void credit(int id, double amount) {
		updateBalance(id, amount);

	}

	private int updateBalance(int id, double amount) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"update Account set balance = balance +  :amount"
						+ " where id = :id");
		query.setParameter("amount", amount);
		query.setParameter("id", id);
		int result = query.executeUpdate();
		System.out.println("Update Account result=" + result );
		return result;
	}

}
