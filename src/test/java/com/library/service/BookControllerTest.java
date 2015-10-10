package com.library.service;

import java.util.List;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.model.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mvc-servlet.xml")
@WebAppConfiguration
public class BookControllerTest {
	
	private final String SUCCESS = "SUCCESS";
	private final String FAIL = "FAIL";
	
	private final Logger logger = LoggerFactory.getLogger(BookControllerTest.class.getName());
	
	private MockMvc mockMvc;
	
	@Autowired
    protected WebApplicationContext webApplicationContext;
	
	@Autowired
	private IBookService bookService;
	
	@Before
	public void setup() {
        this.mockMvc = webAppContextSetup(this.webApplicationContext).build();
	}
	
	private Book generateRandomBook() {
		Book dummyBook = new Book("dummyKitapAdı", "dummyYazarAdı");
		return dummyBook;
	}
	
	private String convertString(Book book) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(book);
	}

	@Test
	public void testFindAll() throws Exception {
		mockMvc
	        .perform(get("/core/findAll"))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.status").value(SUCCESS));
	}
	
	@Test
	public void testSave() throws Exception {
		Book book = generateRandomBook();
		String bookAsJson = convertString(book);
		mockMvc
			.perform(get("/core/save")
					.content(bookAsJson)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.status").value(SUCCESS));
	}
	
	@Test
	public void testSaveWithValidation() throws Exception {
		Book book = generateRandomBook();
		//expected work min 3 validation
		book.setName("a");
		String bookAsJson = convertString(book);
		mockMvc
			.perform(get("/core/save")
					.content(bookAsJson)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.status").value(FAIL));
	}
	
	@Test
	public void testUpdate() throws Exception {
		List<Book> bookList = bookService.findAll();
		if(!bookList.isEmpty()) {
			Book book = bookList.get(0);
			book.setAuthor("updated!");
			String bookAsJson = convertString(book);
			mockMvc
				.perform(get("/core/update")
						.content(bookAsJson)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.status").value(SUCCESS));
		}
	}
	
	@Test
	public void testRemove() throws Exception {
		List<Book> bookList = bookService.findAll();
		if(!bookList.isEmpty()) {
			Book book = bookList.get(0);
			mockMvc	
				.perform(get("/core/remove/" + book.getId()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.status").value(SUCCESS));
		}
	}
}
