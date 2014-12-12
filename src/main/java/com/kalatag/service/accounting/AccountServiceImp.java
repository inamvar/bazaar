package com.kalatag.service.accounting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kalatag.dao.AccountDao;
import com.kalatag.domain.Account;
import com.kalatag.service.CRUDServiceImp;
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
