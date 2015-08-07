package br.com.javapress.application.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.JpaSystemException;
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
		return this.getErrorInfoObject(ExceptionMessages.OPERATION_NOT_ALLOWED.toString(), req.getRequestURL().toString());
    }
	
	@ExceptionHandler(TypeMismatchException.class)
    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    public ErrorInfo handleTypeMismatchException(HttpServletRequest req, TypeMismatchException ex) {
		return getErrorInfoObject(ExceptionMessages.ID_TYPE_MISMATCH.toString(), req.getRequestURL().toString());
    }
	
	public ErrorInfo getErrorInfoObject(String errorMassage, String errorUrl){
		Locale locale = LocaleContextHolder.getLocale();
		String errorMessage = messageSource.getMessage(errorMassage, null, locale);
		return new ErrorInfo(errorUrl,errorMessage);
	}
}
