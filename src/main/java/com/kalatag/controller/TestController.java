package com.kalatag.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	@RequestMapping(value="/merchant", method = RequestMethod.GET)
	public String merchant(Model model) {

		List<Merchant> merchants = merchantService.findAll();
		model.addAttribute("msg", merchants.size());

		
		
		return "test";
	}
	
	@RequestMapping(value="/pass", method = RequestMethod.GET)
	public String generatePassword(Model model) {
		model.addAttribute("msg", Util.generateRandomPassword());
		return "test";
	}
	
	@RequestMapping( method = RequestMethod.GET)
	public String addPerson(Model model) {
		try {
			Person p = new Person();
			p.setFirstName("Mohammad");
			p.setLastName("Namvar");
			p.setGender(Gender.Female);
			p.setBirthday( new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH).parse("1984/09/06"));
			p.setUsername("namvar@gmail.com");
			service.create(p);
//			service.sendRegisterationNotification(p);
			model.addAttribute("msg", p.toString());
			return "test";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

}
