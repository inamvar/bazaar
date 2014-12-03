package com.dariksoft.kalatag.service.merchant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dariksoft.kalatag.dao.MerchantDao;
import com.dariksoft.kalatag.domain.Merchant;
import com.dariksoft.kalatag.domain.PersonRole;
import com.dariksoft.kalatag.service.CRUDServiceImp;
import com.dariksoft.kalatag.service.PersonRoleService;

@Service
public class MerchantServiceImp extends CRUDServiceImp<Merchant> implements MerchantService{
	
	@Autowired
	PersonRoleService roleService;
	
	@Autowired
	MerchantDao merchantDao;
	
	@Override
	@Transactional
	public Merchant create(Merchant merchant){
		 PersonRole role = roleService.findByRoleName("ROLE_MERCHANT");
         if (role == null) {
             PersonRole r = new PersonRole();
             r.setRole("ROLE_MERCHANT");
            r = roleService.create(r);
             merchant.getContactPoint().setPersonRole(r);
         } else {
        	 merchant.getContactPoint().setPersonRole(role);
         }
		//merchant.getContactPoint().setEnabled(true);
		return merchantDao.create(merchant);
	}
	
/*	@Override
	@Transactional
	public Merchant update(Merchant merchant){
		 PersonRole role = roleService.findByRoleName("ROLE_MERCHANT");
         if (role == null) {
             PersonRole r = new PersonRole();
             r.setRole("ROLE_MERCHANT");
            r = roleService.create(r);
             merchant.getContactPoint().setPersonRole(r);
         } else {
        	 merchant.getContactPoint().setPersonRole(role);
         }
	//	merchant.getContactPoint().setEnabled(true);
		return merchantDao.create(merchant);
	}*/
	
}
