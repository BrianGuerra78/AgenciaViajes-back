package mx.com.basantader.AgenciaViajeTA.handler;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import mx.com.basantader.AgenciaViajeTA.exceptions.BusinessException;
import mx.com.basantader.AgenciaViajeTA.exceptions.ResourceNotFoundException;
import mx.com.basantader.AgenciaViajeTA.dto.CustomErrorResponse;
import mx.com.basantader.AgenciaViajeTA.commons.Messages;


import org.slf4j.Logger;

/**
 * Generic error handling mechanism.
 *
 */
@ControllerAdvice
public class ErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(ErrorHandler.class);

    @ResponseStatus(HttpStatus.NOT_FOUND)  // 404
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseBody
	public CustomErrorResponse handleNotFound(ResourceNotFoundException ex) {
		log.error("Entity was not found", ex);
		return new CustomErrorResponse(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND, ex.getMessage() );
	}
	
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)  // 422
	@ExceptionHandler(BusinessException.class)
	@ResponseBody
	public CustomErrorResponse handleNotFound(BusinessException ex) {
		 log.error("Generic exception", ex);
		return new CustomErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(),HttpStatus.UNPROCESSABLE_ENTITY, ex.getDescription());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  // 500
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public CustomErrorResponse handleNotFound(RuntimeException ex) {
		log.error("Generic exception", ex);
		return new CustomErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value()
				,HttpStatus.INTERNAL_SERVER_ERROR,Messages.getMessage(1));
	}


}
