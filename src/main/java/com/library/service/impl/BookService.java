package com.library.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.exception.InconsistentException;
import com.library.model.Book;
import com.library.repository.IBookRepository;
import com.library.service.IBookService;
import com.library.statics.Constants;

@Service
public class BookService implements IBookService {
	
	private final Logger logger = LoggerFactory.getLogger(BookService.class.getName());

	@Autowired
	private IBookRepository bookRepository;

	@Override
	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	@Override
	public Book findById(String id) {
		return bookRepository.findOne(id);
	}

	@Override
	public Book save(Book book) {
		logger.info("Book({}) will be save.", book);
		return bookRepository.save(book);
	}

	@Override
	public Book update(Book book) {
		boolean exist = bookRepository.exists(book.getId());
		if(exist) {
			logger.info("Book({}) will be update.", book);
			return bookRepository.save(book);
		} else {
			logger.error("Book({}) does not exist!", book);
			throw new InconsistentException(Constants.BOYLE_BIR_KITAP_YOK);
		}
	}

	@Override
	public void remove(String id) {
		boolean exist = bookRepository.exists(id);
		if(exist) {
			logger.info("Book({}) will be remove.", id);
			bookRepository.delete(id);
		} else {
			logger.error("Book({}) does not exist!" , id);
			throw new InconsistentException(Constants.BOYLE_BIR_KITAP_YOK);
		}
	}
}
