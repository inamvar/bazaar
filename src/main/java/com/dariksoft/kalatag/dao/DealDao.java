package com.dariksoft.kalatag.dao;

import java.util.List;

import com.dariksoft.kalatag.domain.Deal;
import com.dariksoft.kalatag.domain.ItemStatus;

public interface DealDao  extends GenericDao<Deal> {
	
	List<Deal> findDealsByStatus(ItemStatus status);

}
