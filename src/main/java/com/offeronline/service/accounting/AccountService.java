package com.offeronline.service.accounting;

import com.offeronline.domain.Account;
import com.offeronline.service.CRUDService;

public interface AccountService extends CRUDService<Account>{

	void debit(int id, double amount);
	void credit(int id, double amount);
}
