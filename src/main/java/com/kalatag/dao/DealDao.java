package com.kalatag.dao;

import java.util.List;

import com.kalatag.domain.Deal;
import com.kalatag.domain.DealLabel;
import com.kalatag.domain.ItemCategory;
import com.kalatag.domain.ItemStatus;

public interface DealDao  extends GenericDao<Deal> {
	
	List<Deal> findDealsByStatus(ItemStatus status);
	List<Deal> findDealsByLabelAndStatus(DealLabel label, ItemStatus status);
	List<Deal> findDealsByStatusAndNotLabel(DealLabel label, ItemStatus status);
	List<Deal> findDealsByCategoryAndStatusAndNotLabel(ItemCategory category, DealLabel label, ItemStatus status);
	
	int getSold(Deal deal);
	
}
