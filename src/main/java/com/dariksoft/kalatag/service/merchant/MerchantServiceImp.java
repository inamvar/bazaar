package com.dariksoft.kalatag.service.merchant;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dariksoft.kalatag.dao.GenericDao;
import com.dariksoft.kalatag.domain.Merchant;
import com.dariksoft.kalatag.service.CRUDServiceImp;
import com.dariksoft.kalatag.service.listener.GenericMessageCreator;

@Service
public class MerchantServiceImp extends CRUDServiceImp<Merchant> implements MerchantService{
	
	@Autowired
	JmsTemplate template;
	
	@Autowired
	GenericDao<Merchant> dao;
	
	@Autowired
	Destination auditing;
	
	@Override
	@Transactional
	public Merchant create(Merchant m) {
		Merchant merchant = dao.create(m);
/*		template.setDefaultDestination(auditing);
		MessageCreator messageCreator = new GenericMessageCreator<String>(merchant.getName() + " created!");
		template.send(messageCreator);*/
		return merchant;
	}

	
	
	
}
