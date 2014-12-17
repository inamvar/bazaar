package com.kalatag.dao;

import com.kalatag.domain.Merchant;
import com.kalatag.domain.Person;

public interface MerchantDao extends GenericDao<Merchant>{

	Merchant findByPerson(Person person);
}
