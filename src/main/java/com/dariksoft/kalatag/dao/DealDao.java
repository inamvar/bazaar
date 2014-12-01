package com.dariksoft.kalatag.dao;

import java.util.List;

import com.dariksoft.kalatag.domain.Deal;
import com.dariksoft.kalatag.domain.DealLabel;
import com.dariksoft.kalatag.domain.ItemCategory;
import com.dariksoft.kalatag.domain.ItemStatus;

public interface DealDao  extends GenericDao<Deal> {
	
	List<Deal> findDealsByStatus(ItemStatus status);
	List<Deal> findDealsByLabelAndStatus(DealLabel label, ItemStatus status);
	List<Deal> findDealsByStatusAndNotLabel(DealLabel label, ItemStatus status);
	List<Deal> findDealsByCategoryAndStatusAndNotLabel(ItemCategory category, DealLabel label, ItemStatus status);
	int getSold(Deal deal);
	
}
