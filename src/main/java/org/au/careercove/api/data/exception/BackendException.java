package org.au.careercove.api.data.exception;

public class BackendException extends RuntimeException {

	private static final long serialVersionUID = 2085402272278479323L;
	
	public BackendException(String message) {
        super(message);
    }
	
	public BackendException(String message, Throwable cause) {
        super(message, cause);
    }
}
