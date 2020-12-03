package com.izasoft.jcart;

import static org.junit.Assert.assertEquals;

import javax.sql.DataSource;

import org.junit.runner.RunWith;
import org.junit.Test;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JCartCoreApplicationTest {
	
	@Autowired DataSource datasource;
	
	@Test
	public void testDummy() throws SQLException
	{
		String schema = datasource.getConnection().getCatalog();
		assertEquals("jcart", schema);
	}

}
