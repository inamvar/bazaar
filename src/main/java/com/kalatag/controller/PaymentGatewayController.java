package com.kalatag.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kalatag.domain.Deal;
import com.kalatag.domain.DealOption;
import com.kalatag.domain.Order;
import com.kalatag.domain.Person;
import com.kalatag.domain.Transaction;
import com.kalatag.domain.TransactiontStatus;
import com.kalatag.exception.DealExpiredException;
import com.kalatag.gateway.PaymentGateway;
import com.kalatag.service.CustomerService;
import com.kalatag.service.DealOptionService;
import com.kalatag.service.DealService;
import com.kalatag.service.ItemCategoryService;
import com.kalatag.service.accounting.TransactionService;
import com.kalatag.service.order.OrderService;
import com.kalatag.service.person.PersonService;

@Controller
@RequestMapping(value = "/payment/gateway")
public class PaymentGatewayController {

	private static final Logger logger = LoggerFactory
			.getLogger(PaymentGatewayController.class);

	@Autowired
	TransactionService txnService;
	@Autowired
	private DealService dealService;

	@Autowired
	private ItemCategoryService categoryService;

	@Autowired
	private DealOptionService optionService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private PersonService personService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private PaymentGateway paymentGateway;

	@RequestMapping(value = "response/saman", method = RequestMethod.POST)
	public String samanResult(@RequestParam("State") String state,
			@RequestParam("RefNum") String referenceNumber,
			@RequestParam("ResNum") String reservationNumber,
			@RequestParam("MID") String merchantId,
			@RequestParam("TraceNo") String traceNumber) {

		return null;

	}

	@RequestMapping(value = "response/test", method = RequestMethod.POST)
	public String testResult(@RequestParam("State") String state,
			@RequestParam("RefNum") String referenceNumber,
			@RequestParam("ResNum") String reservationNumber,
			@RequestParam("MID") String merchantId,
			@RequestParam("TraceNo") String traceNumber, Model uiModel)
			throws Exception {
		
		logger.debug("result received from bank .......");
		
		Transaction txn = txnService.find(Integer.parseInt(reservationNumber));
		
		if (txn != null && txn.getReferenceNumber() != referenceNumber
				&& txn.getStatus() != TransactiontStatus.PAID) {
			txn.setState(state);
			txn.setReferenceNumber(referenceNumber);
			txn.setReservationNumber(reservationNumber);
			txn.setTraceNumber(traceNumber);
			txn.setMerchantId(merchantId);
			txn = paymentGateway.receiveResponse(txn);
			if (txn.getStatus() == TransactiontStatus.PAID) {
				Order order = insertOrder(txn);
				uiModel.addAttribute("order", order);
				return "website/orderconfirm";
			} else{
				//TODO: check for reason and save error code and show better message
				throw new Exception("Payment Failed");
			}
		} else
			throw new Exception("Broken Transaction! Order failed.");

	}

	private Order insertOrder(Transaction txn) throws DealExpiredException {


		Deal deal = dealService.find(txn.getDealId());
		DealOption option = optionService.find(txn.getDealOptionId());
		Person customer = txn.getPerson();

		logger.debug("---------Respose from bank recevied. saving order---------");
		logger.debug("RefNum=" + txn.getReferenceNumber());
		logger.debug("ResNum=" + txn.getReservationNumber());
		logger.debug("deal= " + deal.getId() + ", " + deal.getName());
		logger.debug("option= " + option.getId() + ", " + option.getName());
		logger.debug("customer= " + customer.getId() + ", "
				+ customer.getFirstName() + " " + customer.getLastName());
		
		Order order = new Order(deal, option, customer, txn.getQty());
		return orderService.create(order);
	}

}
