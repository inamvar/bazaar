package com.dariksoft.kalatag.service.listener;

import java.util.Locale;

import javax.mail.BodyPart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.dariksoft.kalatag.domain.Person;

@Component("registrationListener")
public class RegistrationListener {
	
	private Logger log = LoggerFactory.getLogger(RegistrationListener.class);
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private MessageSource messageSource;
	
	public void onMessage(Person person) {
		
		try {
			log.info("Registeration: " + person + " registered successfully.");
			sendEmail(person);		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendEmail(Person person) {
        
		try {
	          StringBuffer htmlText = new StringBuffer();
//	          Locale locale = new Locale("fa");
//	          LocaleResolver
	          
	          Locale locale = LocaleContextHolder.getLocale();
	          
	          htmlText.append("<html><body>");
	          htmlText.append("<p>" + messageSource.getMessage("email.registration.header", null, locale));
	          htmlText.append("<p>" +messageSource.getMessage("email.registration.body", null, locale));
	          htmlText.append("<p>" +messageSource.getMessage("email.registration.footer", null, locale));
//	          htmlText.append("<p>Dear " + person.getFirstName() + ",<br><br> Congratulations! your registeration is done successfully, thank you for using kalatag."
//	          		+ "please change your generated password after first login.</p>");
//	          htmlText.append("<p>Your password is: " + person.getPassword() +" </p>");
//	          htmlText.append("<br><p>www.kalatag.com</p>");

	          htmlText.append("</body></html>");
	         
	          MimeMessage mimeMessage = mailSender.createMimeMessage();
	          MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
	          MimeMultipart multipart = new MimeMultipart("related");
	          BodyPart messageBodyPart = new MimeBodyPart();
	          
	          //add html part
	          messageBodyPart.setContent(htmlText.toString(), "text/html");
	          multipart.addBodyPart(messageBodyPart);
	          
	          mimeMessage.setContent(multipart);       
	          helper.setTo(person.getUsername());
	          helper.setSubject("Kalatag registeration");
	          mailSender.send(mimeMessage);
	         log.info("For registeration notification an email to "+ person.getUsername() + " has been sent.");
	          
	      } catch (Exception e) {
	          e.printStackTrace();
	      }

        
    }

}
