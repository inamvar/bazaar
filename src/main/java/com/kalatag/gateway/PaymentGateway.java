package com.kalatag.gateway;

import com.kalatag.domain.Transaction;

public interface PaymentGateway {

	Transaction receiveResponse(Transaction txn);
	String GetRequestUrl(Transaction txn);
}
