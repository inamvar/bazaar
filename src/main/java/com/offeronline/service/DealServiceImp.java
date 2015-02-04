package com.offeronline.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.offeronline.dao.DealDao;
import com.offeronline.domain.Deal;
import com.offeronline.domain.DealLabel;
import com.offeronline.domain.ItemCategory;
import com.offeronline.domain.ItemStatus;
import com.offeronline.domain.Merchant;

@Service
public class DealServiceImp extends CRUDServiceImp<Deal> implements DealService {

	@Autowired
	DealDao dealDao;

	@Override
	@Transactional
	public int getSold(Deal deal) {
		return dealDao.getSold(deal);
	}

	@Override
	@Transactional
	public List<Deal> findDealsByStatus(ItemStatus status) {
		return dealDao.findDealsByStatus(status);
	}

	@Override
	@Transactional
	public List<Deal> findDealsByLabelAndStatus(DealLabel label,
			ItemStatus status) {
		return dealDao.findDealsByLabelAndStatus(label, status);
	}

	@Override
	@Transactional
	public List<Deal> findDealsByStatusAndNotLabel(DealLabel label,
			ItemStatus status) {

		return dealDao.findDealsByStatusAndNotLabel(label, status);
	}

	@Override
	@Transactional
	public List<Deal> findDealsByCategoryAndStatusAndNotLabel(
			ItemCategory category, DealLabel label, ItemStatus status) {
		return dealDao.findDealsByCategoryAndStatusAndNotLabel(category, label,
				status);
	}

	@Override
	@Transactional
	public List<Deal> findDealsByMerchant(Merchant merchant) {
		return dealDao.findDealsByMerchant(merchant);
	}

	@Override
	@Transactional
	public List<Deal> findSimilars(Deal deal) {
		return dealDao.findSimilars(deal);
	}

	@Override
	public boolean isExpired(Deal deal) {
		if (deal.getValidity().compareTo(new Date()) < 0){
			return true;
		}else{
			return false;
		}
	}
}
