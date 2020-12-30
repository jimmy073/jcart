package com.izasoft.jcart;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.izasoft.jcart.common.service.EmailService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JCartCoreApplicationTest {
	
	@Autowired EmailService emailService;
	
	@Test
	public void sendMailTest() {
		emailService.sendEmail("irakizajimmy1@gmail.com", "JCart - Test Mail", 
				"This is a test email from JCart");
	}

}
