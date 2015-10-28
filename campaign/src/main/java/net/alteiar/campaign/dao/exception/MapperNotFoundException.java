package net.alteiar.campaign.dao.exception;

public class MapperNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MapperNotFoundException() {
		super();
	}

	public MapperNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public MapperNotFoundException(String message) {
		super(message);
	}

	public MapperNotFoundException(Throwable cause) {
		super(cause);
	}

}
