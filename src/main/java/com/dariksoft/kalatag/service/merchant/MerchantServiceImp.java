package com.dariksoft.kalatag.service.merchant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dariksoft.kalatag.dao.GenericDao;
import com.dariksoft.kalatag.domain.Merchant;
import com.dariksoft.kalatag.service.CRUDServiceImp;
import com.dariksoft.kalatag.util.Util;

@Service
public class MerchantServiceImp extends CRUDServiceImp<Merchant> implements MerchantService{
	
	@Autowired
	GenericDao<Merchant> genericDao;
	
	
	@Override
	@Transactional
	public Merchant create(Merchant t) {
		t.getContactPoint().setUsername(t.getContact().getEmail());
		String securedPassword = Util.toSHA256(Util.generateRandomPassword()) ;
		t.getContactPoint().setPassword(securedPassword);
		return genericDao.create(t);

	}
	
}
