package net.alteiar.campaign.view.article.content.edit;

import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import net.alteiar.campaign.article.content.ContentText;

public class ContentTextEditView extends ContentEditView<ContentText> {

	@Override
	public Node createContentView() {

		HBox hbox = new HBox();

		hbox.getChildren().add(getPublicView());

		String text = getContentData().getText();

		TextArea txtArea = new TextArea(text);
		hbox.getChildren().add(txtArea);
		HBox.setHgrow(txtArea, Priority.ALWAYS);

		return hbox;
	}
}
