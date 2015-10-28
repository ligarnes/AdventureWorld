package net.alteiar.campaign.view.article.content.edit;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import net.alteiar.campaign.article.content.ContentSection;
import net.alteiar.campaign.bo.article.content.ContentBO;
import net.alteiar.campaign.view.controller.Controller;

public class ContentSectionEditView extends ContentEditView<ContentSection> {

	@Override
	public Node createContentView() {

		String title = getContentData().getTitle();

		TextField textField = new TextField(title);
		textField.setFont(new Font("Arial", 20));

		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER_LEFT);
		hbox.getChildren().add(textField);
		HBox.setHgrow(textField, Priority.ALWAYS);

		VBox childrenVbox = new VBox();

		for (ContentBO content : getContent().getChildrenContent()) {

			Controller subView = ContentEditViewFactory.createContentView(this, content);
			subView.setRequest(getRequest());
			childrenVbox.getChildren().add(subView.getView());
		}

		ScrollPane sp = new ScrollPane();
		sp.setContent(childrenVbox);
		sp.setFitToWidth(true);

		VBox vBox = new VBox();
		vBox.getChildren().add(hbox);
		vBox.getChildren().add(sp);

		return vBox;
	}

}
