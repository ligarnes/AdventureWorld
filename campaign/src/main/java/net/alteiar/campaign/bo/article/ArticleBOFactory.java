package net.alteiar.campaign.bo.article;

import net.alteiar.campaign.article.Article;
import net.alteiar.campaign.bo.article.content.ContentBOFactory;
import net.alteiar.campaign.dao.article.ArticleDaoFactory;

public class ArticleBOFactory {

	private final ArticleDaoFactory daoFactory;
	private final ContentBOFactory contentFactory;

	public ArticleBOFactory(ArticleDaoFactory factory) {

		this.daoFactory = factory;
		this.contentFactory = new ContentBOFactory(daoFactory);
	}

	public ContentBOFactory getContentBOFactory() {
		return contentFactory;
	}

	public ArticleBO createNewArticle(String name) {

		Article article = new Article();
		article.setName(name);

		return new ArticleBOImpl(contentFactory, daoFactory.getArticleDao(), article);
	}

	public ArticleBO getArticle(String contentId) {

		Article article = daoFactory.getArticleDao().findById(contentId);

		return new ArticleBOImpl(contentFactory, daoFactory.getArticleDao(), article);
	}

}
