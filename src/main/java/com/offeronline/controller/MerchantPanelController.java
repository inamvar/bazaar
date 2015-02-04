package com.offeronline.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.offeronline.domain.Coupon;
import com.offeronline.domain.CouponStatus;
import com.offeronline.domain.Deal;
import com.offeronline.domain.Merchant;
import com.offeronline.domain.Person;
import com.offeronline.service.CouponService;
import com.offeronline.service.DealService;
import com.offeronline.service.MerchantPaymentService;
import com.offeronline.service.merchant.MerchantService;
import com.offeronline.service.person.PersonService;
import com.offeronline.util.Util;

@Controller
@RequestMapping(value = "/merchant")
public class MerchantPanelController {
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private MerchantService merchantService;
	@Autowired
	private PersonService personService;

	@Autowired
	private DealService dealService;

	@Autowired
	private CouponService couponService;
	
	@Autowired 
	private MerchantPaymentService mpService;

	@RequestMapping(value = "/panel", method = RequestMethod.GET)
	public String panel(
			@RequestParam(value = "index", required = false) Integer index,
			Locale locale, Model uiModel) {

		uiModel = fillModel(uiModel, locale);

		if (index == null)
			index = 0;
		uiModel.addAttribute("idx", index);

		return "website/merchant/home";
	}

	@RequestMapping(value = "/panel/verify", method = RequestMethod.POST)
	public String verify(
			@RequestParam(value = "couponCode", required = true) String code,
			Locale locale, Model uiModel) {

		Coupon coupon = couponService.verify(code);
		if (coupon != null) {
			uiModel.addAttribute("verifyCoupon", coupon);
		} else {
			uiModel.addAttribute("verifyError",
					messageSource.getMessage("coupon.invalid", null, locale));
		}
		uiModel = fillModel(uiModel, locale);

		uiModel.addAttribute("idx", 0);

		return "website/merchant/home";
	}

	@RequestMapping(value = "/panel/redeem", method = RequestMethod.POST)
	public String redeem(
			@RequestParam(value = "couponCode", required = true) String code,
			Locale locale, Model uiModel) {

		Coupon coupon = couponService.findByCode(code);
		if (coupon != null) {
			String username = Util.getCurrentUserName();
			if (coupon.getDeal().getMerchant().getContactPoint().getUsername()
					.equals(username)) {
				if (coupon.getStatus() != CouponStatus.REDEMED) {
					coupon = couponService.redeem(coupon);
					if (coupon != null) {
						uiModel.addAttribute("redeemCoupon", coupon);
						uiModel.addAttribute("redeemSuccess", messageSource
								.getMessage("coupon.redeem.success", null,
										locale));

					}
				} else {
					String[] args = new String[1];
					args[0] = coupon.getRedeemDate().toString();
					uiModel.addAttribute("redeemError", messageSource
							.getMessage("coupon.redeem.already", args, locale));
				}
			} else {
				uiModel.addAttribute("redeemError", messageSource.getMessage(
						"coupon.redeem.merchant.invalid", null, locale));
			}
		} else {

			uiModel.addAttribute("redeemError",
					messageSource.getMessage("coupon.notfound", null, locale));
		}
		uiModel = fillModel(uiModel, locale);

		uiModel.addAttribute("idx", 1);

		return "website/merchant/home";
	}

	private Model fillModel(Model uiModel, Locale locale) {

		String username = Util.getCurrentUserName();
		if (username != null) {
			Person person = personService.findByUserName(username);
			if (person != null) {
				Merchant merchant = merchantService.findByPerson(person);
				List<Deal> deals = dealService.findDealsByMerchant(merchant);
				uiModel.addAttribute("deals", deals);
				uiModel.addAttribute("merchant", merchant);
				uiModel.addAttribute("balance", mpService.GetBalance(merchant));
				uiModel.addAttribute("sold", mpService.getTotalSold(merchant));
				uiModel.addAttribute("paid", mpService.GetTotalPaid(merchant));
				uiModel.addAttribute("payments", mpService.findByMerchant(merchant));
				uiModel.addAttribute("totalCustomers", merchantService.getCustomers(merchant).size());

			}

		}

		uiModel.addAttribute("title", messageSource.getMessage(
				"website.merchant.panel.title", null, locale));

		return uiModel;
	}

}
