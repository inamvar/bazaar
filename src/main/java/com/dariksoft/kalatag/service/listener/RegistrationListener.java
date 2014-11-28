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
	          
//	         Locale locale = LocaleContextHolder.getLocale();
//	         Locale locale = new Locale("es_ES");
			Locale locale = new Locale("fa_IR");
	          
	          log.info("locale=" + locale);
	          
	          StringBuffer htmlText = new StringBuffer();
	          htmlText.append("<html><body>");
	          htmlText.append("<p>" + messageSource.getMessage("email.registration.header", new String[]{person.getFirstName(), person.getLastName()}, locale));
	          htmlText.append("<p>" + messageSource.getMessage("email.registration.body", new String[]{person.getUsername(), person.getPassword()}, locale));
	          htmlText.append("<p>" + messageSource.getMessage("email.registration.footer", null, locale));
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
