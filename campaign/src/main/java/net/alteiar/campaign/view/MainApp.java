package net.alteiar.campaign.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import net.alteiar.campaign.dao.DaoFactory;
import net.alteiar.campaign.dao.MongoDaoFactory;
import net.alteiar.campaign.dao.article.ArticleDaoFactory;
import net.alteiar.campaign.view.controller.ArticleEditController;
import net.alteiar.campaign.view.controller.ArticleViewController;
import net.alteiar.campaign.view.model.Request;
import net.alteiar.campaign.view.model.ViewDispatcher;

public class MainApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		DaoFactory daoFactory = new MongoDaoFactory();

		ArticleDaoFactory articleFactory = daoFactory.createArticleFactory();

		StackPane root = new StackPane();
		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.show();

		ViewDispatcher dispatcher = new ViewDispatcher(primaryStage);
		dispatcher.registerView(ArticleViewController.VIEW_NAME, new ArticleViewController(articleFactory));
		dispatcher.registerView(ArticleEditController.VIEW_NAME, new ArticleEditController(articleFactory));

		Request requestView = new Request();
		requestView.put("article.id", "56244928854befcf4adc6088");
		dispatcher.navigate(ArticleViewController.VIEW_NAME, requestView);

	}
}
