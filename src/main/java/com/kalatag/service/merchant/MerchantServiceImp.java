package com.kalatag.service.merchant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kalatag.dao.MerchantDao;
import com.kalatag.domain.Merchant;
import com.kalatag.domain.Person;
import com.kalatag.domain.PersonRole;
import com.kalatag.service.CRUDServiceImp;
import com.kalatag.service.PersonRoleService;

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

	@Override
	@Transactional
	public Merchant findByPerson(Person person) {
		return merchantDao.findByPerson(person);
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
