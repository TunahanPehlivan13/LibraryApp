package com.library.controller.ajax;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.library.data.enums.Status;
import com.library.exception.LibraryException;
import com.library.statics.Constants;
import com.library.util.JSONUtil;

public abstract class AjaxAbstractController {
	
	private final Logger logger = LoggerFactory.getLogger(AjaxAbstractController.class.getName());

	/**
	 * Sadece LibraryException sınıfından türetilen hatalar kullanıcıya gösterilir.
	 * 
	 **/
	@ExceptionHandler(LibraryException.class)
	protected @ResponseBody ObjectNode handleException(LibraryException exception) {
		logger.error(exception.getMessage());
		ObjectNode message = JSONUtil.makeResponse(Status.FAIL, exception.getMessage(), null);
		return message;
	}
	
	@ExceptionHandler(Exception.class)
	protected @ResponseBody ObjectNode handleException(Exception exception) {
		logger.error(exception.getMessage());
		ObjectNode message = JSONUtil.makeResponse(Status.FAIL, Constants.SUNUCU_HATASI, null);
		return message;
	}
}
