package com.offeronline.gateway;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.offeronline.domain.Transaction;
import com.offeronline.domain.TransactiontStatus;
import com.offeronline.service.accounting.TransactionService;

public class TestGateway implements PaymentGateway {

	// "http://localhost:8080/offeronline/test/payment/simulator"
	// "http://localhost:8080/offeronline/payment/gateway/test";

	private String url;
	private String returnUrl;
	private String merchantId;

	@Autowired
	TransactionService txnService;

	public TestGateway() {
		super();
	}

	public TestGateway(String url, String returnUrl, String merchantId) {
		super();
		this.url = url;
		this.merchantId = merchantId;
		this.returnUrl = returnUrl;
	}

	@Override
	public Transaction receiveResponse(Transaction txn) {
		if (txn.getState().equals("OK"))
			txn.setStatus(TransactiontStatus.PAID);
		else
			txn.setStatus(TransactiontStatus.FAILED);
		txn.setResponseDate(new Date());
		return txnService.update(txn);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	@Override
	public String GetRequestUrl(Transaction txn) {
		Double d =  txn.getAmount();
		txn.setMerchantId(this.merchantId);
		String ret = this.url + "?Amount=" + d.intValue()+ "&MID="
				+ this.merchantId + "&ResNum=" + txn.getReservationNumber()
				+ "&RedirectURL=" + this.returnUrl;
		return ret;
	}

}
