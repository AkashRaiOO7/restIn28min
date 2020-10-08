package in28minutes.rest.webservices.user.exception;

import java.time.LocalDate;

public class ExceptionResponse {

	private String msg;
	private LocalDate exceptionDate;
	private String details;

	public ExceptionResponse(String msg, LocalDate exceptionDate, String details) {
		super();
		this.msg = msg;
		this.exceptionDate = exceptionDate;
		this.details = details;
	}

	public String getMsg() {
		return msg;
	}

	public LocalDate getExceptionDate() {
		return exceptionDate;
	}

	public String getDetails() {
		return details;
	}

}
