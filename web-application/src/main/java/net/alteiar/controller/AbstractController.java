package net.alteiar.controller;

public abstract class AbstractController {

	protected String handleAllException(Exception ex) {

		return "error/exception";
	}

	public abstract String handleException(Exception ex);
}
