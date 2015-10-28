package net.alteiar.campaign.view.model;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import net.alteiar.campaign.view.controller.Controller;

public class ViewDispatcher {

	private Stage primaryStage;
	private Map<String, Controller> controllers;

	public ViewDispatcher(Stage primaryStage) {

		this.primaryStage = primaryStage;
		controllers = new HashMap<>();
	}

	public void registerView(String view, Controller controller) {

		controller.setDispatcher(this);
		controllers.put(view, controller);
	}

	private Controller getController(String viewName) {

		return controllers.get(viewName);
	}

	public void navigate(String view) {

		navigate(view, new Request());
	}

	public void navigate(String viewName, Request request) {

		Controller controller = getController(viewName);
		if (controller == null) {

			throw new IllegalArgumentException(String.format("no view found with name %s", viewName));
		}

		StackPane root = new StackPane();
		controller.setRequest(request);
		root.getChildren().add(controller.getView());
		primaryStage.getScene().setRoot(root);
	}
}
