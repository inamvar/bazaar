package com.dariksoft.kalatag.service;

import java.util.List;

import com.dariksoft.kalatag.domain.Deal;
import com.dariksoft.kalatag.domain.ItemStatus;

public interface DealService extends CRUDService<Deal> {
	
	int getSold();
	List<Deal> findDealsByStatus(ItemStatus status);
}
