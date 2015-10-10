package com.library.controller.ajax;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.library.data.enums.Status;
import com.library.model.Book;
import com.library.service.IBookService;
import com.library.statics.Constants;
import com.library.util.JSONUtil;

@Controller
@RequestMapping("/core")
public class BookController extends AjaxAbstractController {

	private final Logger logger = LoggerFactory.getLogger(BookController.class.getName());
	
	@Autowired
	private IBookService bookService;
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public @ResponseBody ObjectNode findAll() {
		List<Book> bookList = bookService.findAll();
		StringBuilder content = new StringBuilder()
			.append(bookList.size())
			.append(" adet kitap başarıyla getirildi!");
		ObjectNode response = JSONUtil
				.makeResponse(Status.SUCCESS, content.toString(), bookList);
		return response;
	}

	@RequestMapping(value="/save", method=RequestMethod.POST)
	public @ResponseBody ObjectNode save(@RequestBody final Book book) {
		bookService.save(book);
		ObjectNode response = JSONUtil
				.makeResponse(Status.SUCCESS, Constants.KITAP_BASARYILA_KAYDEDILDI, book);
		return response;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public @ResponseBody ObjectNode update(@RequestBody final Book book) {
		bookService.update(book);
		ObjectNode  response = JSONUtil
				.makeResponse(Status.SUCCESS, Constants.KITAP_BASARIYLA_GUNCELLENDI, book);
		return response;
	}

	@RequestMapping(value="/remove/{id}", method=RequestMethod.DELETE)
	public @ResponseBody ObjectNode remove(@PathVariable final String id) {
		bookService.remove(id);
		ObjectNode response = JSONUtil
				.makeResponse(Status.SUCCESS, Constants.KITAP_BASARIYLA_SILINDI, null);
		return response;
	}
}
