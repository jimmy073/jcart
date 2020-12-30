package com.izasoft.jcart.common.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.izasoft.jcart.JCartException;

@Component
public class EmailService {
	
	private static final JCLogger logger = JCLogger.getLogger(EmailService.class);
	
	@Autowired JavaMailSender javaMailSender;
	
	@Value("${support.email}")
	String suppotEmail;
	
	public void sendEmail(String to, String subject, String content) {
		try {
			//Preparing Message with Spring helper!!
			
			final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
			final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
			
			message.setTo(to);
			message.setSubject(subject);
			message.setFrom(suppotEmail);
			message.setText(content, true /*isHtml though I don't know why*/);
			
			javaMailSender.send(message.getMimeMessage());
			
		} catch (Exception e) {
			logger.error(e);
			throw new JCartException("Unable to Send Email");
			}
	
	}
}
