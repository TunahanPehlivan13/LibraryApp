package com.library.validator;

import static junit.framework.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.library.exception.LibraryException;
import com.library.model.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mvc-servlet.xml")
@WebAppConfiguration
public class BeforeSaveValidatorTest {
	
	@Autowired
	private BeforeSaveValidator beforeSaveValidator;

	@Test
	@SuppressWarnings("deprecation")
	public void testOnBeforeSaveExpectedSuccess() {
		Book book = new Book("Kitap", "Yazar");
		
		try {
			beforeSaveValidator.onBeforeSave(book, null);
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	@SuppressWarnings("deprecation")
	public void testOnBeforeSaveExpectedMin3Validation() {
		Book book = new Book("K", "Yazar");
		
		try {
			beforeSaveValidator.onBeforeSave(book, null);
			fail();
		} catch (LibraryException le) {
			//success
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	@SuppressWarnings("deprecation")
	public void testOnBeforeSaveExpectedNotEmptyValidation() {
		Book book = new Book("", "");
		
		try {
			beforeSaveValidator.onBeforeSave(book, null);
			fail();
		} catch (LibraryException le) {
			//success
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
