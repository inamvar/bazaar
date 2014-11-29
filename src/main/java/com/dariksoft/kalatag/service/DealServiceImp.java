package com.dariksoft.kalatag.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dariksoft.kalatag.dao.DealDao;
import com.dariksoft.kalatag.domain.Deal;
import com.dariksoft.kalatag.domain.ItemStatus;

@Service
public class DealServiceImp extends CRUDServiceImp<Deal> implements DealService{

	@Autowired DealDao dealDao;
	
	@Override
	public int getSold() {
	
		return 0;
	}

	@Override
	@Transactional
	public List<Deal> findDealsByStatus(ItemStatus status) {
		return dealDao.findDealsByStatus(status);
	}

	
}
