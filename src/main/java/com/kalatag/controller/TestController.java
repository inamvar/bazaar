package com.kalatag.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.kalatag.domain.Gender;
import com.kalatag.domain.Merchant;
import com.kalatag.domain.Person;
import com.kalatag.service.merchant.MerchantService;
import com.kalatag.service.person.PersonService;
import com.kalatag.util.Util;

@Controller
@RequestMapping(value = "/test")
public class TestController {

	public PersonService getService() {
		return service;
	}

	public void setService(PersonService service) {
		this.service = service;
	}

	@Autowired
	PersonService service;
	@Autowired
	MerchantService merchantService;

	@RequestMapping(value = "/payment/simulator", method = { RequestMethod.POST, RequestMethod.GET })
	public String paymentGatewaySimolator(@RequestParam("Amount") int amount,
			@RequestParam("MID") String merchantId,
			@RequestParam("ResNum") String reservationNumber,
			@RequestParam("RedirectURL") String redirectUrl, Model uiModel) {
		
		
		String referenceNumber = UUID.randomUUID().toString().substring(0, 6)
				.toUpperCase();
		Random rand = new Random();
		int traceNo = rand.nextInt(999999) + 1000;
		/*try {

			RestTemplate restTemplate = new RestTemplate();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("TraceNo",traceNo);
			params.put("MID", merchantId);
			params.put("ResNum", reservationNumber);
			params.put("ResNum", referenceNumber);
			params.put("State", "OK");
			String urlParameters = "?State={State}&RefNum={RefNum}&ResNum={ResNum}&MID={MID}&TraceNo={TraceNo}";			
			restTemplate.postForLocation(redirectUrl + urlParameters , String.class, params);
		} catch (Exception e) {
			e.getStackTrace();
		}*/
		
		uiModel.addAttribute("TraceNo" , traceNo);
		uiModel.addAttribute("MID" , merchantId);
		uiModel.addAttribute("ResNum", reservationNumber);
		uiModel.addAttribute("RefNum",referenceNumber);
		uiModel.addAttribute("Amount",amount);
		uiModel.addAttribute("RedirectURL",redirectUrl);
		System.out.println("request delivered to gateway. \n redirectUrl="+ redirectUrl);
		return "bank-simulation";
	}

	@RequestMapping(value = "/merchant", method = RequestMethod.GET)
	public String merchant(Model model) {

		List<Merchant> merchants = merchantService.findAll();
		model.addAttribute("msg", merchants.size());

		return "test";
	}

	@RequestMapping(value = "/pass", method = RequestMethod.GET)
	public String generatePassword(Model model) {
		model.addAttribute("msg", Util.generateRandomPassword());
		return "test";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String addPerson(Model model) {
		try {
			Person p = new Person();
			p.setFirstName("Mohammad");
			p.setLastName("Namvar");
			p.setGender(Gender.Female);
			p.setBirthday(new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH)
					.parse("1984/09/06"));
			p.setUsername("namvar@gmail.com");
			service.create(p);
			// service.sendRegisterationNotification(p);
			model.addAttribute("msg", p.toString());
			return "test";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

}
