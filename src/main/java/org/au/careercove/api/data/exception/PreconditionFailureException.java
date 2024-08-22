package org.au.careercove.api.data.exception;

public class PreconditionFailureException extends RuntimeException {

	private static final long serialVersionUID = 2085402272278479323L;
	
	public PreconditionFailureException(String message) {
        super(message);
    }
	
	public PreconditionFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
