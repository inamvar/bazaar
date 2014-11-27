package com.dariksoft.kalatag.service.listener;

import javax.mail.BodyPart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.dariksoft.kalatag.domain.Person;

@Component("registerationListener")
public class RegisterationListener {
	
	private Logger log = LoggerFactory.getLogger(RegisterationListener.class);
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void onMessage(Person person) {
		
		try {
			log.info("Registeration: " + person + " registered successfully." + " Person pass=" + person.getPassword());
			sendEmail(person);		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendEmail(Person person) {
        
		try {
	          StringBuffer htmlText = new StringBuffer();
	          htmlText.append("<html><body>");
	          htmlText.append("<p>Dear " + person.getFirstName() + ",<br><br> Congratulations! your registeration is done successfully, thank you for using kalatag."
	          		+ "please change your generated password after first login.</p>");
	          htmlText.append("<p>Your password is: " + person.getPassword() +", </p>");
	          htmlText.append("<br><br><p>www.kalatag.com</p>");
	          htmlText.append("</body>");
	          htmlText.append("</html>");
	         
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
