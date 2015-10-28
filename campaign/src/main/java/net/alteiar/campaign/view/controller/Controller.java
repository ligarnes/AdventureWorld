package net.alteiar.campaign.view.controller;

import javafx.scene.Node;
import net.alteiar.campaign.view.model.Request;
import net.alteiar.campaign.view.model.ViewDispatcher;

public abstract class Controller {

	private ViewDispatcher dispatcher;
	private Request request;

	public void setDispatcher(ViewDispatcher dispatcher) {

		System.out.println("set dispatcher " + dispatcher);
		this.dispatcher = dispatcher;
	}

	public void setRequest(Request request) {

		this.request = request;
	}

	public ViewDispatcher getDispatcher() {

		return dispatcher;
	}

	public Request getRequest() {
		return request;
	}

	public abstract Node getView();
}
