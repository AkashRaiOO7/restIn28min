package in28minutes.rest.webservices.user;

/**
 * @ResponseStatus(code = HttpStatus.NOT_FOUND)
 * I have commented out this portion. Because I have handled this better in CustomizedResponseEntityExceptionHandler
 */
public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String message) {
		super(message);
	}
}