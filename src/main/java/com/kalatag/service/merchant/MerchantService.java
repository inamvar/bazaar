package com.kalatag.service.merchant;

import com.kalatag.domain.Merchant;
import com.kalatag.domain.Person;
import com.kalatag.service.CRUDService;

public interface MerchantService extends CRUDService<Merchant>{

	Merchant findByPerson(Person person);
}
