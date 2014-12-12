package com.kalatag.service.accounting;

import com.kalatag.domain.Account;
import com.kalatag.service.CRUDService;

public interface AccountService extends CRUDService<Account>{

	void debit(int id, double amount);
	void credit(int id, double amount);
}
