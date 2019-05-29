package cl.previred.solution.exception;

import org.springframework.http.HttpStatus;

public class PreviredException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final HttpStatus httpStatus;
	private String message;

	public PreviredException(String message, HttpStatus httpStatus) {
		super();
		this.httpStatus = httpStatus;
		this.setMessage(message);

	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String getMessage() {
		return message;
	}

	void setMessage(String message) {
		this.message = message;
	}

}
