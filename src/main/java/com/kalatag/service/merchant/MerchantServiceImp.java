package com.kalatag.service.merchant;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kalatag.dao.MerchantDao;
import com.kalatag.domain.City;
import com.kalatag.domain.Customer;
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

	@Override
	@Transactional
	public List<Customer> getCustomers(Merchant merchant) {
		return merchantDao.getCustomers(merchant);
	}

	@Override
	@Transactional
	public Map<City, Integer> getCityCount(Merchant merchant) {
		return merchantDao.getCityCount(merchant);
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
