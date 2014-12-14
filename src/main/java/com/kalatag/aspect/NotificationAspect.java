package com.kalatag.aspect;

import java.util.ArrayList;
import java.util.Date;

import javax.jms.Destination;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.kalatag.domain.Account;
import com.kalatag.domain.Customer;
import com.kalatag.domain.Journal;
import com.kalatag.domain.JournalType;
import com.kalatag.domain.Merchant;
import com.kalatag.domain.Order;
import com.kalatag.domain.Person;
import com.kalatag.exception.AccountCreditNotEnoughException;
import com.kalatag.service.accounting.AccountService;
import com.kalatag.service.listener.GenericMessageCreator;
import com.kalatag.service.listener.RegistrationListener;
import com.kalatag.service.person.PersonService;
import com.kalatag.util.Util;

@Aspect
public class NotificationAspect {

	@Autowired
	JmsTemplate template;

	@Autowired
	Destination emailNotification;

	@Autowired
	Destination changePasswordNotification;

	@Autowired
	Destination resetPasswordNotification;
	
	@Autowired
	Destination journalNotification;

	@Autowired
	PersonService personService;
	
	@Autowired
	AccountService accountService;

	private Logger log = LoggerFactory.getLogger(RegistrationListener.class);

	@Around("within(com.kalatag.service.CRUDService+) && target(com.kalatag.service.merchant.MerchantServiceImp) && execution(* create(..))")
	public Merchant aroundMerchantCreate(ProceedingJoinPoint pjp)
			throws Throwable {

		Object[] args = pjp.getArgs();
		Merchant merchant = (Merchant) args[0];
		String password = Util.generateRandomPassword();
		String encryptedPassword = Util.toSHA256(password);
		Person person = merchant.getContactPoint();
		person.setPassword(encryptedPassword);
		person.setUsername(merchant.getContact().getEmail());
		setAccount(person);
		try {
			merchant = (Merchant) pjp.proceed();
			person.setPassword(password);
			template.setDefaultDestination(emailNotification);
			MessageCreator messageCreator = new GenericMessageCreator<Merchant>(
					merchant);
			template.send(messageCreator);
			person.setPassword(encryptedPassword);
			return merchant;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Around("within(com.kalatag.service.CRUDService+) && target(com.kalatag.service.person.PersonServiceImp) && execution(* create(..))")
	public Person aroundPersonCreate(ProceedingJoinPoint pjp) throws Throwable {

		Object[] args = pjp.getArgs();
		Person person = (Person) args[0];
		String password = Util.generateRandomPassword();
		String encryptedPassword = Util.toSHA256(password);
		log.debug("pass=" + encryptedPassword);
		person.setPassword(encryptedPassword);
		setAccount(person);
		try {
			person = (Person) pjp.proceed();
			person.setPassword(password);
			template.setDefaultDestination(emailNotification);
			MessageCreator messageCreator = new GenericMessageCreator<Person>(
					person);
			template.send(messageCreator);
			person.setPassword(encryptedPassword);
			return person;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	private void setAccount(Person person) {
		Account account = new Account();
		account.setName(person.getFirstName() +" "+ person.getLastName());
		account.setJournals(new ArrayList<Journal>());
		person.setAccount(account);
		account.setPerson(person);
	}

	@Around("within(com.kalatag.service.CRUDService+) && target(com.kalatag.service.CustomerServiceImp) && execution(* create(..))")
	public Customer aroundCustomerCreate(ProceedingJoinPoint pjp)
			throws Throwable {

		Object[] args = pjp.getArgs();
		Customer customer = (Customer) args[0];
		String password = Util.generateRandomPassword();
		String encryptedPassword = Util.toSHA256(password);
		
		customer.setPassword(encryptedPassword);
		
		Account account = new Account();
		account.setName(customer.getFirstName() +" "+ customer.getLastName());
		account.setJournals(new ArrayList<Journal>());
		customer.setAccount(account);
		account.setPerson(customer);
		
		log.debug("Account name: "+customer.getAccount().getName());
		try {
			customer = (Customer) pjp.proceed();
			customer.setPassword(password);
			template.setDefaultDestination(emailNotification);
			MessageCreator messageCreator = new GenericMessageCreator<Customer>(
					customer);
			template.send(messageCreator);
			customer.setPassword(encryptedPassword);
			
			return customer;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	
	@Before("within(com.kalatag.service.CRUDService+) && target(com.kalatag.service.order.OrderServiceImp) && execution(* create(..))")
	public void beforeOrderCreate(JoinPoint jp) throws Throwable {
		Object[] args = jp.getArgs();
		Order order = (Order) args[0];
	
	}

	@AfterReturning(pointcut = "within(com.kalatag.service.CRUDService+) && target(com.kalatag.service.order.OrderServiceImp) && execution(* create(..))", returning = "order")
	public void afterOrderCreate(JoinPoint jp, Order order) throws Throwable {
		log.debug("after create order id=" + order.getId()
				+ ", sending order to qserver...");
		
		template.setDefaultDestination(emailNotification);
		MessageCreator messageCreator = new GenericMessageCreator<Order>(order);
		template.send(messageCreator);
		
		/* Creating a journal and sending it into journal queue*/
/*		Journal journal = new Journal();
		journal.setDate(new Date());
		journal.setAccount(order.getPerson().getAccount());
		journal.setAmount(order.getTotalPrice());
		journal.setOrder(order);
		journal.setType(JournalType.DEBIT);
		
		template.setDefaultDestination(journalNotification);
		MessageCreator journalMessageCreator = new GenericMessageCreator<Journal>(journal);
		template.send(journalMessageCreator);*/
		/* end of creating journal*/
		
	}

	@Around("within(com.kalatag.service.CRUDService+) && target(com.kalatag.service.person.PersonServiceImp) && execution(* changePassword(..))")
	public int afterPerosnChangePassword(ProceedingJoinPoint pjp) {
		int ret = 0;
		Object[] args = pjp.getArgs();
		int id = (Integer) args[0];
		Person person = personService.find(id);
		if (person != null) {
			String password = (String) args[1];
			String encryptedPassword = Util.toSHA256(password);
			

			try {
				ret = (Integer) pjp.proceed();
				person.setPassword(password);
				template.setDefaultDestination(changePasswordNotification);

				MessageCreator messageCreator = new GenericMessageCreator<Person>(
						person);
				template.send(messageCreator);
				person.setPassword(encryptedPassword);
				return ret;
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			} catch (Throwable e) {
				e.printStackTrace();
				return -2;
			}
		} else
			return -3;
	}

	@Around("within(com.kalatag.service.CRUDService+) && target(com.kalatag.service.person.PersonServiceImp) && execution(* resetPassword(..))")
	public int afterPerosnResetPassword(ProceedingJoinPoint pjp) {
		int ret = 0;
		Object[] args = pjp.getArgs();
		int id = (Integer) args[0];
		Person person = personService.find(id);
		if (person != null) {
			String password = Util.generateRandomPassword();
			 String encryptedPassword = Util.toSHA256(password);
		

			try {
				ret = (Integer) pjp.proceed(new Object[] { id, password });
				person.setPassword(password);
				template.setDefaultDestination(resetPasswordNotification);

				MessageCreator messageCreator = new GenericMessageCreator<Person>(
						person);
				template.send(messageCreator);
				person.setPassword(encryptedPassword);
				return ret;
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			} catch (Throwable e) {
				e.printStackTrace();
				return -2;
			}
		} else
			return -3;
	}
	
	

}
