package com.offeronline.service;

import java.util.List;

import com.offeronline.domain.Deal;
import com.offeronline.domain.DealLabel;
import com.offeronline.domain.ItemCategory;
import com.offeronline.domain.ItemStatus;
import com.offeronline.domain.Merchant;

public interface DealService extends CRUDService<Deal> {

	int getSold(Deal deal);

	List<Deal> findDealsByStatus(ItemStatus status);

	List<Deal> findDealsByLabelAndStatus(DealLabel label, ItemStatus status);

	List<Deal> findDealsByStatusAndNotLabel(DealLabel label, ItemStatus status);

	List<Deal> findDealsByCategoryAndStatusAndNotLabel(ItemCategory category,
			DealLabel label, ItemStatus status);
	
	List<Deal> findDealsByMerchant(Merchant merchant);
	
	List<Deal> findSimilars(Deal deal);
	
	boolean isExpired(Deal deal);
}
