package com.kalatag.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kalatag.dao.MerchantPaymentDao;
import com.kalatag.domain.Merchant;
import com.kalatag.domain.MerchantPayment;

@Service
public class MerchantPaymentServiceImp extends CRUDServiceImp<MerchantPayment> implements MerchantPaymentService{

	
	@Autowired
	MerchantPaymentDao merchantPeymentDao;
	
	@Override
	@Transactional
	public double GetBalance(Merchant merchant) {
		return merchantPeymentDao.GetBalance(merchant);
	}

	@Override
	@Transactional
	public double GetTotalPaid(Merchant merchant) {
		return merchantPeymentDao.GetTotalPaid(merchant);
	}

	@Override
	@Transactional
	public double getTotalSold(Merchant merchant) {
		return merchantPeymentDao.getTotalSold(merchant);
	}

	@Override
	@Transactional
	public List<MerchantPayment> findByMerchant(Merchant merchant) {
		return merchantPeymentDao.findByMerchant(merchant);
	}

	@Override
	@Transactional
	public List<MerchantPayment> findByMerchant(Merchant merchant,
			Date fromDate, Date toDate) {
		return merchantPeymentDao.findByMerchant(merchant, fromDate, toDate);
	}

	@Override
	@Transactional
	public List<MerchantPayment> findByMerchant(Merchant merchant,
			int maximumRowResult) {
		return merchantPeymentDao.findByMerchant(merchant, maximumRowResult);
	}

}
