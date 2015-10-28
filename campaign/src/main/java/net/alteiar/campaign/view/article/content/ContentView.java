package net.alteiar.campaign.view.article.content;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import net.alteiar.campaign.article.content.Content;
import net.alteiar.campaign.bo.article.content.ContentBO;
import net.alteiar.campaign.view.controller.Controller;

public abstract class ContentView<E extends Content> extends Controller {

	private ContentBO content;

	public void load(ContentBO content) {

		this.content = content;
	}

	protected ContentBO getContent() {

		return content;
	}

	protected E getContentData() {

		return (E) content.getContentData();
	}

	@Override
	public Node getView() {

		return createContentView();
	}

	protected abstract Node createContentView();

	/**
	 * create a Node that contain a standard vision icon
	 * 
	 * @return
	 */
	protected Node getPublicView() {

		ImageView image;

		if (getContent().isPublic()) {

			image = new ImageView(
					new Image(getClass().getResourceAsStream("/javafx/circle_green.png"), 24, 24, true, true));
		} else {

			image = new ImageView(
					new Image(getClass().getResourceAsStream("/javafx/circle_red.png"), 24, 24, true, true));
		}

		VBox vbox = new VBox();
		vbox.setAlignment(Pos.TOP_LEFT);
		vbox.getChildren().add(image);

		vbox.setPadding(new Insets(10, 10, 10, 10));

		return vbox;
	}
}
