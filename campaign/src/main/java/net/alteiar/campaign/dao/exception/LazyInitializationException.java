package net.alteiar.campaign.dao.exception;

public class LazyInitializationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public LazyInitializationException() {
		super("The object must be loaded");
	}

}
