package net.alteiar.db.installer.exception;

public class ParsingException extends Exception {

	private static final long serialVersionUID = 1L;

	public ParsingException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParsingException(String message) {
		super(message);
	}

	public ParsingException(Throwable cause) {
		super(cause);
	}

}
