package com.library.service;

import java.util.List;

import com.library.model.Book;

public interface IBookService {

	List<Book> findAll();
	
	Book findById(String id);
	
	Book save(Book book);
	
	Book update(Book book);
	
	void remove(String id);
}
