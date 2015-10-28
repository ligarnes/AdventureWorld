package net.alteiar.campaign.view;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import net.alteiar.campaign.bo.article.ArticleBO;
import net.alteiar.campaign.bo.article.content.ContentBO;
import net.alteiar.campaign.view.article.content.edit.ContentEditViewFactory;
import net.alteiar.campaign.view.controller.Controller;

public class ArticleEditor extends Controller {

	private ArticleBO article;

	public void load(ArticleBO article) {

		this.article = article;
	}

	protected ArticleBO getArticle() {

		return article;
	}

	@Override
	public Node getView() {

		VBox vBox = new VBox();

		String title = getArticle().getName();

		Label lblTitle = new Label(title);
		lblTitle.setFont(new Font("Arial", 30));

		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().add(lblTitle);
		HBox.setHgrow(lblTitle, Priority.ALWAYS);

		vBox.getChildren().add(hbox);

		for (ContentBO section : getArticle().getContent()) {

			Controller sectionView = ContentEditViewFactory.createContentView(this, section);
			sectionView.setRequest(getRequest());
			vBox.getChildren().add(sectionView.getView());
		}

		return vBox;
	}

}
