package com.offeronline.gateway;

import com.offeronline.domain.Transaction;

public interface PaymentGateway {

	Transaction receiveResponse(Transaction txn);
	String GetRequestUrl(Transaction txn);
}
