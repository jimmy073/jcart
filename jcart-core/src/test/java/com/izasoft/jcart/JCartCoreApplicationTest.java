package com.izasoft.jcart;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.izasoft.jcart.common.service.EmailService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JCartCoreApplicationTest {
	
	@Autowired 
	EmailService emailService;
	@Autowired
	DataSource dataSource;
	
	
	@Test
	@Sql({"/data.sql"})
	public void testDummy() throws SQLException {
	    String schema = dataSource.getConnection().getCatalog();
	    assertTrue("jcart".equalsIgnoreCase(schema));
	}
	
//	@Test
//	public void sendMailTest() {
//		emailService.sendEmail("irakizajimmy1@gmail.com", "JCart - Test Mail", 
//				"This is a test email from JCart");
//	}

}
