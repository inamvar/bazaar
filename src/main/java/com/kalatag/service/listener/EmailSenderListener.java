package com.kalatag.service.listener;

import java.util.Locale;

import javax.activation.DataHandler;
import javax.jms.Destination;
import javax.mail.BodyPart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.kalatag.domain.Customer;
import com.kalatag.domain.Merchant;
import com.kalatag.domain.Order;
import com.kalatag.domain.Person;
import com.kalatag.service.order.OrderService;

@Component("emailSenderListener")
public class EmailSenderListener {

	private Logger log = LoggerFactory.getLogger(EmailSenderListener.class);

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	JmsTemplate template;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private OrderService orderService;

	@Autowired
	Destination orderConfirmation;

	Locale locale = new Locale("fa");

	public void onMessage(Customer customer) {

		try {
			log.info("Registeration customer: " + customer
					+ " registered successfully.");
			sendCustomerRegisterEmail(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onMessage(Merchant merchant) {

		try {
			log.info("Registeration merchant: " + merchant.getName() + " " + merchant.getContactPoint().getUsername()
					+ " registered successfully.");
			sendCustomerRegisterEmail(merchant.getContactPoint());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onMessage(Order order) {
		try {
			log.debug("---------->new order received from Qserver. order id="
					+ order.getId());
			log.debug("Order confirmation: id:" + order.getId() + ", Customer: "
					+ order.getPerson().getFirstName() + " "
					+ order.getPerson().getLastName() + ", Status: "
					+ order.getStatus());

			template.setDefaultDestination(orderConfirmation);
			MessageCreator messageCreator = new GenericMessageCreator<Order>(
					order);
			template.send(messageCreator);
			sendOrderCreateEmail(order);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendCustomerRegisterEmail(Person person) {

		try {
			Locale locale = LocaleContextHolder.getLocale();

			String[] params = new String[4];
			params[0] = person.getFirstName();
			params[1] = person.getLastName();
			params[2] = person.getUsername();
			params[3] = person.getPassword();

			String htmlText = messageSource.getMessage(
					"email.user.registration", params, locale);

			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
					false, "utf-8");
			MimeMultipart multipart = new MimeMultipart("related");
			BodyPart messageBodyPart = new MimeBodyPart();

			// add html part
			messageBodyPart.setContent(htmlText, "text/html; charset=utf-8");
			multipart.addBodyPart(messageBodyPart);

			mimeMessage.setContent(multipart);
			helper.setTo(person.getUsername());
			helper.setSubject(messageSource.getMessage(
					"email.user.registration.subject", null, locale));
			mailSender.send(mimeMessage);
			log.info("For registeration notification an email to "
					+ person.getUsername() + " has been sent.");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void sendOrderCreateEmail(Order order) {

		try {

			String[] params = new String[9];
			params[0] = order.getPerson().getFirstName();
			params[1] = order.getDeal().getName();
			params[2] = order.getOption().getName();
			params[3] = order.getPerson().getFirstName() + " "
					+ order.getPerson().getLastName();
			params[4] = order.getDeal().getMerchant().getName();
			params[5] = order.getDeal().getMerchant().getContact().getAddress();
			params[6] = order.getOrderDate().toString();
			params[7] = order.getOption().getPrice() + "";
			params[8] = messageSource.getMessage("kalatag.currency", null,
					locale);
			;

			String htmlText = messageSource.getMessage("email.order.new",
					params, locale);

			// log.info(htmlText);

			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true,
					"utf-8");
			MimeMultipart multipart = new MimeMultipart("related");
			BodyPart messageBodyPart = new MimeBodyPart();

			// add html part
			messageBodyPart.setContent(htmlText, "text/html; charset=utf-8");
			multipart.addBodyPart(messageBodyPart);

			// add image
			byte[] thumbnail = order.getDeal().getThumbnail();
			if (thumbnail != null) {
				messageBodyPart = new MimeBodyPart();
				ByteArrayDataSource dealImgDs = new ByteArrayDataSource(
						thumbnail, "image/jpeg");
				messageBodyPart.setDataHandler(new DataHandler(dealImgDs));
				messageBodyPart.setHeader("Content-ID", "<image>");
				multipart.addBodyPart(messageBodyPart);
			}

			mimeMessage.setContent(multipart);
			helper.setTo(order.getPerson().getUsername());
			helper.setSubject(messageSource.getMessage(
					"email.order.new.subject", null, locale));
			mailSender.send(mimeMessage);
			log.info("For order Issue an email to "
					+ order.getPerson().getUsername() + " has been sent.");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}