package com.offeronline.service.accounting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.offeronline.dao.AccountDao;
import com.offeronline.domain.Account;
import com.offeronline.service.CRUDServiceImp;
@Service
public class AccountServiceImp extends CRUDServiceImp<Account> implements AccountService {

	@Autowired
	AccountDao accountDao;
	@Override
	@Transactional
	public void debit(int id, double amount) {
		accountDao.debit(id, amount);
	}

	@Override
	@Transactional
	public void credit(int id, double amount) {
		accountDao.credit(id, amount);		
	}

}
