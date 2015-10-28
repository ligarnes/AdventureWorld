package net.alteiar.campaign.view.article.content;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import net.alteiar.campaign.article.content.ContentSection;
import net.alteiar.campaign.bo.article.content.ContentBO;
import net.alteiar.campaign.view.controller.Controller;

public class ContentSectionView extends ContentView<ContentSection> {

	@Override
	public Node createContentView() {

		String title = getContentData().getTitle();

		Label lblTitle = new Label(title);
		lblTitle.setFont(new Font("Arial", 20));

		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER_LEFT);
		hbox.getChildren().add(lblTitle);

		VBox childrenVbox = new VBox();

		for (ContentBO content : getContent().getChildrenContent()) {

			Controller subView = ContentViewFactory.createContentView(this, content);
			childrenVbox.getChildren().add(subView.getView());
		}

		ScrollPane sp = new ScrollPane();
		sp.setContent(childrenVbox);

		VBox vBox = new VBox();
		vBox.getChildren().add(hbox);
		vBox.getChildren().add(sp);

		return vBox;
	}

}
