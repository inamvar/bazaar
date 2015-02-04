package com.offeronline.dao;

import com.offeronline.domain.Account;

public interface AccountDao extends GenericDao<Account> {
	void debit(int id, double amount);
	void credit(int id, double amount);
}
