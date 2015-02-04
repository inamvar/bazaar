package com.offeronline.service;

import java.util.Date;
import java.util.List;

import com.offeronline.domain.Merchant;
import com.offeronline.domain.MerchantPayment;

public interface MerchantPaymentService extends CRUDService<MerchantPayment>{

	double GetBalance(Merchant merchant);
	double GetTotalPaid(Merchant merchant);
	double getTotalSold(Merchant merchant);
	List<MerchantPayment> findByMerchant(Merchant merchant);
	List<MerchantPayment> findByMerchant(Merchant merchant, Date fromDate, Date toDate);
	List<MerchantPayment> findByMerchant(Merchant merchant, int maximumRowResult);
	
}
