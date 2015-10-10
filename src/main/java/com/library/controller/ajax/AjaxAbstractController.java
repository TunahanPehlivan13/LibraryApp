package com.library.controller.ajax;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.library.data.enums.Status;
import com.library.exception.LibraryException;
import com.library.statics.Constants;
import com.library.util.JSONUtil;

public abstract class AjaxAbstractController {

	/**
	 * Sadece LibraryException sınıfından türetilen hatalar kullanıcıya gösterilir.
	 * 
	 **/
	@ExceptionHandler(LibraryException.class)
	public @ResponseBody ObjectNode handleException(LibraryException exception) {
		ObjectNode message = JSONUtil.makeResponse(Status.FAIL, exception.getMessage(), null);
		return message;
	}
	
	@ExceptionHandler(Exception.class)
	public @ResponseBody ObjectNode handleException(Exception exception) {
		ObjectNode message = JSONUtil.makeResponse(Status.FAIL, Constants.SUNUCU_HATASI, null);
		return message;
	}
}
