package org.au.careercove.api.data;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.au.careercove.api.data.exception.PreconditionFailureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RESTExceptionAdvice extends ResponseEntityExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(RESTExceptionAdvice.class);
	
	@ExceptionHandler({ PreconditionFailureException.class })
	public ResponseEntity<String> handleRunTimeException(PreconditionFailureException e) {
		return error(HttpStatus.BAD_REQUEST, e);
	}

	@ExceptionHandler({ RuntimeException.class })
	public ResponseEntity<String> handleRunTimeException(RuntimeException e) {
		return error(HttpStatus.INTERNAL_SERVER_ERROR, e);
	}

	private ResponseEntity<String> error(HttpStatus status, Exception e) {
		log.error("=====================================================");
		log.error("Handling generic exception: ", ExceptionUtils.getRootCause(e));
		log.error("=====================================================");
		return ResponseEntity.status(status).body(
			"{\"message\": \"" + ExceptionUtils.getRootCauseMessage(e) + "\"}");
	}
}
