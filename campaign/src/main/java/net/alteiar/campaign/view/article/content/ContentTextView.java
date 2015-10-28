package net.alteiar.campaign.view.article.content;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import net.alteiar.campaign.article.content.ContentText;
import net.alteiar.campaign.view.controller.ArticleEditController;

public class ContentTextView extends ContentView<ContentText> {

	@Override
	public Node createContentView() {

		HBox hbox = new HBox();

		hbox.getChildren().add(getPublicView());

		String text = getContentData().getText();

		Label lblText = new Label(text);
		lblText.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {

				mouseClicked(event);
			}
		});

		hbox.getChildren().add(lblText);
		HBox.setHgrow(lblText, Priority.ALWAYS);

		return hbox;
	}

	private void mouseClicked(Event event) {

		MouseEvent evt = (MouseEvent) event;
		if (evt.getClickCount() > 1) {

			System.out.println("Click count " + evt.getClickCount());
			System.out.println(getDispatcher());
			getDispatcher().navigate(ArticleEditController.VIEW_NAME, getRequest());

		}
	}
}
