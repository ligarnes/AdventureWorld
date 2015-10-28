package net.alteiar.campaign.view.controller;

import javafx.scene.Node;
import net.alteiar.campaign.bo.article.ArticleBO;
import net.alteiar.campaign.bo.article.ArticleBOFactory;
import net.alteiar.campaign.dao.article.ArticleDaoFactory;
import net.alteiar.campaign.view.ArticleEditor;

public class ArticleEditController extends Controller {

	public static final String VIEW_NAME = "article.edit";

	private ArticleDaoFactory articleFactory;

	public ArticleEditController(ArticleDaoFactory articleFactory) {

		this.articleFactory = articleFactory;
	}

	@Override
	public Node getView() {

		String articleId = (String) getRequest().getValue("article.id");

		ArticleBOFactory factory = new ArticleBOFactory(articleFactory);
		ArticleBO article = factory.getArticle(articleId);

		ArticleEditor node = new ArticleEditor();
		node.load(article);
		node.setRequest(getRequest());
		node.setDispatcher(getDispatcher());

		return node.getView();
	}

}
