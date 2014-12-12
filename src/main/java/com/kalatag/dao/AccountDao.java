package com.kalatag.dao;

import com.kalatag.domain.Account;

public interface AccountDao extends GenericDao<Account> {
	void debit(int id, double amount);
	void credit(int id, double amount);
}
