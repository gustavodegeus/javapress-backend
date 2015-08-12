package br.com.javapress.application.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.javapress.application.exception.ErrorInfo;
import br.com.javapress.application.exception.ExceptionMessages;

@ControllerAdvice
public class ExceptionController {

	@Autowired
    private MessageSource messageSource;
     
	
	@ExceptionHandler(JpaSystemException.class)
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorInfo jpaSystemException(HttpServletRequest req, JpaSystemException ex) {
		return this.getErrorInfoObject(ExceptionMessages.OPERATION_NOT_ALLOWED.toString(), req.getRequestURL().toString(), ex.getMessage());
    }
	
	@ExceptionHandler(TypeMismatchException.class)
    @ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ResponseBody
    public ErrorInfo handleTypeMismatchException(HttpServletRequest req, TypeMismatchException ex) {
		return getErrorInfoObject(ExceptionMessages.ID_TYPE_MISMATCH.toString(), req.getRequestURL().toString(), ex.getMessage());
    }
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
    public ErrorInfo emptyResultDataAccessException(HttpServletRequest req, EmptyResultDataAccessException ex) {
		return getErrorInfoObject(ExceptionMessages.RECORD_NOT_FOUND.toString(), req.getRequestURL().toString(), ex.getMessage());
    }
	
	@ExceptionHandler(TransactionSystemException.class)
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
    public ErrorInfo transactionSystemException(HttpServletRequest req, TransactionSystemException ex) {
		return getErrorInfoObject(ExceptionMessages.OPERATION_NOT_ALLOWED.toString(), req.getRequestURL().toString(), ex.getMessage());
    }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
	@ResponseBody
    public ErrorInfo transactionSystemException(HttpServletRequest req, MethodArgumentNotValidException ex) {
		return getErrorInfoObject(ExceptionMessages.INVALID_JSON_FORMAT.toString(), req.getRequestURL().toString(), ex.getMessage());
    }
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorInfo defaultHandler(HttpServletRequest req, Exception ex){
		return getErrorInfoObject(ExceptionMessages.INTERNAL_SERVER_ERROR.toString(), req.getRequestURL().toString(), ex.getMessage());
	}
	
	public ErrorInfo getErrorInfoObject(String errorMassage, String errorUrl, String error){
		Locale locale = LocaleContextHolder.getLocale();
		String errorMessage = messageSource.getMessage(errorMassage, null, locale);
		return new ErrorInfo(errorUrl,errorMessage,error);
	}
}
