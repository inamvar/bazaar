package com.offeronline.service.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.offeronline.dao.OrderDao;
import com.offeronline.domain.Coupon;
import com.offeronline.domain.Deal;
import com.offeronline.domain.DealOption;
import com.offeronline.domain.ItemStatus;
import com.offeronline.domain.Order;
import com.offeronline.domain.OrderStatus;
import com.offeronline.domain.Person;
import com.offeronline.domain.Transaction;
import com.offeronline.domain.TransactiontStatus;
import com.offeronline.exception.DealExpiredException;
import com.offeronline.service.CRUDServiceImp;
import com.offeronline.service.CouponService;
import com.offeronline.service.DealOptionService;
import com.offeronline.service.DealService;
import com.offeronline.service.accounting.TransactionService;
import com.offeronline.service.person.PersonService;
import com.offeronline.util.Util;

@Service
public class OrderServiceImp extends CRUDServiceImp<Order> implements
		OrderService {

	private static final Logger logger = LoggerFactory
			.getLogger(OrderServiceImp.class);

	@Autowired
	DealService dealService;

	@Autowired
	OrderDao orderDao;

	@Autowired
	CouponService couponService;

	@Autowired
	PersonService personService;

	@Autowired
	DealOptionService optionService;

	@Autowired
	TransactionService txnService;

	@Override
	@Transactional
	public List<Order> confirmOrder(Order order) {

		Deal deal = order.getDeal();
		System.out.println("deal.id = " + deal.getId());
		int minimum = deal.getMinCoupon();
		List<Order> orders = new ArrayList<Order>();
		if (dealService.getSold(deal) >= minimum) {

			List<Order> pendings = this.findPendingOrders(deal);
			boolean exist = false;
			for (Order pending : pendings) {
				if (pending.getId() == order.getId()) {
					exist = true;
				}
				orders.add(pending);

			}

			if (!exist)
				orders.add(order);

			if (orders.size() > 0) {
				for (Order ord : orders) {

					List<Coupon> coupons = new ArrayList<Coupon>();
					for (int i = 0; i < ord.getQuantity(); i++) {

						Coupon coupon = new Coupon();
						coupon.setDeal(deal);
						coupon.setPrice(ord.getOption().getPrice());
						coupon.setOrder(ord);
						coupons.add(coupon);

					}
					ord.setCoupons(coupons);
					ord.setStatus(OrderStatus.DONE);
					ord.setFinilizeDate(new Date());
					ord = update(ord);
				}
			}
		}

		return orders;
	}

	@Override
	@Transactional
	public List<Order> findPendingOrders(Deal deal) {
		return orderDao.findPendingOrders(deal);
	}

	@Override
	@Transactional
	public List<Order> findOrdersByDeal(Deal deal) {
		return orderDao.findOrdersByDeal(deal);
	}

	@Override
	public Transaction buy(int dealId, int optionId, int qty)
			throws DealExpiredException {
		logger.debug("---------new order---------");
		Deal deal = dealService.find(dealId);
		logger.debug("deal= " + deal.getId() + ", " + deal.getName());
		DealOption option = optionService.find(optionId);
		logger.debug("option= " + option.getId() + ", " + option.getName());
		logger.debug("username= " + Util.getCurrentUserName());
		Person customer = personService.findByUserName(Util
				.getCurrentUserName());
		logger.debug("customer= " + customer.getId() + ", "
				+ customer.getFirstName() + " " + customer.getLastName());
		Date now = new Date();
		if ((deal.getValidity().compareTo(now) < 0)
				|| deal.getStatus() != ItemStatus.ON) {
			throw new DealExpiredException();
		} else {
			Transaction txn = new Transaction();
			txn.setDate(now);
			double amount = (deal.getPrice()
					- ((option.getDiscount() / 100) * deal.getPrice())) * qty;

			txn.setAmount(amount);
			txn.setStatus(TransactiontStatus.PENDING);
			txn.setDealId(dealId);
			txn.setDealOptionId(optionId);
			txn.setQty(qty);
			txn.setPerson(customer);
			txn = txnService.create(txn);
			txn.setReservationNumber(txn.getId() + "");

			logger.debug("reservation number=" + txn.getReservationNumber());
			return txn;
		}

	}

	@Override
	@Transactional
	public List<Order> findOrdersByCustomer(Person customer) {
		return orderDao.findOrdersByCustomer(customer);
	}

	@Override
	@Transactional
	public boolean CheckMinimumOrder(Order order) {
		Deal deal = order.getDeal();
		int minimum = deal.getMinCoupon();
		if (dealService.getSold(deal) >= minimum) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	@Transactional
	public List<Order> findOrders(Date startDate, Date endDate,

			String customerFirstName, String customerLastName,String merchantName, String dealName,OrderStatus status, int id) {
		return orderDao.findOrders(startDate, endDate, customerFirstName,customerLastName,
				merchantName, dealName, status, id);
	}
	
	
	@Override
	@Transactional
	public int updateStatus(List<Order> orders, OrderStatus status){
		return orderDao.updateStatus(orders, status);
	}
}
