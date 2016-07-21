package de.htwberlin.mae.exception;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import de.htwberlin.mae.model.ErrorInformation;



@ControllerAdvice
class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {
	
	Logger log = LogManager.getRootLogger();
	
	

	//handle conversion errors e.g. if user enters
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(ConversionFailedException.class)
	public ResponseEntity<Object> handleConversionException(ConversionFailedException ex, WebRequest request){
		log.info("handleConversionException");
		//List<String> errors = new ArrayList<String>();
		//errors.add(ex.getValue() + " is type of " + ex.getSourceType() + ", but should be type of " + ex.getTargetType());
		String instance = extractUriFromWebRequest(request);
		String title = ex.getRootCause().getMessage();
		ErrorInformation errorInformation = new ErrorInformation(DateTime.now().toString(), "about:blank", title, HttpStatus.CONFLICT, ex.getLocalizedMessage(), instance);
	    //return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	    return new ResponseEntity<Object>(errorInformation, new HttpHeaders(), errorInformation.getStatus());
	}
	
	/*
	@ExceptionHandler(InvalidGrantException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public void handleAuthenticationException(InvalidGrantException ex) {
		log.info("BAAAMS");
		//String instance = extractUriFromWebRequest(request);
		//String title = "Authorization failed";
        //ErrorInformation errorInformation = new ErrorInformation(DateTime.now().toString(), "about:blank", title, HttpStatus.UNAUTHORIZED, ex.getLocalizedMessage(), instance);
        //return new ResponseEntity<Object>(errorInformation, new HttpHeaders(), errorInformation.getStatus());
    }
	*/
	
	/**
	 * method to extract path from webrequest object
	 * @param request
	 * @return String
	 */
	private String extractUriFromWebRequest(WebRequest request){
		return request.getDescription(false).split("=")[1];
	}
	
}