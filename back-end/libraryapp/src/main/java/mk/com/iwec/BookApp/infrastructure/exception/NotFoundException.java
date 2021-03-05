package mk.com.iwec.BookApp.infrastructure.exception;

public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NotFoundException() {
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundException(String message) {
		super(message);

	}

	public NotFoundException(Throwable cause) {
		super(cause);
	}

}
