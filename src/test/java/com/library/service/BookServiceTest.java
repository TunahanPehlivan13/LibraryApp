package com.library.service;

import static org.mockito.Matchers.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.library.model.Book;
import com.library.repository.IBookRepository;
import com.library.service.impl.BookService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mvc-servlet.xml")
public class BookServiceTest {

	@InjectMocks
	private BookService bookService;
	
	@Mock
	private IBookRepository bookRepository; 
	
	Book book = null;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		book = new Book();
		book.setId("1");
		book.setName("a");
		book.setAuthor("b");
	}
	
	@Test
	public void testSave(){
		bookService.save(book);
		verify(bookRepository, times(1)).save(book);
	}
	
	@Test
	public void testUpdate() {
		when(bookRepository.exists(anyString())).thenReturn(true);
		bookService.update(book);
		verify(bookRepository, times(1)).save(book);
	}
	
	@Test
	public void testRemove() {
		when(bookRepository.exists(anyString())).thenReturn(true);
		bookService.remove(anyString());
		verify(bookRepository, times(1)).delete(anyString());
	}
	
	@Test
	public void testFindAll() {
		bookService.findAll();
		verify(bookRepository, times(1)).findAll();
	}
}
