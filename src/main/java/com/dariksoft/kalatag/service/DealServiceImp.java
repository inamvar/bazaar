package com.dariksoft.kalatag.service;

import org.springframework.stereotype.Service;

import com.dariksoft.kalatag.domain.Deal;

@Service
public class DealServiceImp extends CRUDServiceImp<Deal> implements DealService{

	@Override
	public int getSold() {
	
		return 0;
	}

	
}
