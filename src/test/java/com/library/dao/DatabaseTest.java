package com.library.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.library.service.IBookService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mvc-servlet.xml")
public class DatabaseTest {

	@Autowired
	private IBookService bookService;
	
	@Test
	public void testConnection() {
		try {
			bookService.findById("1");
		} catch (Exception e) {
			fail("Database connection failed!");
		}
	}
}
