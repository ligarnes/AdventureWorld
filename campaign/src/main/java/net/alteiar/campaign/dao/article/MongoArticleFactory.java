package net.alteiar.campaign.dao.article;

import net.alteiar.campaign.dao.MongoConnection;
import net.alteiar.campaign.dao.article.content.ContentDao;
import net.alteiar.campaign.dao.article.content.ContentDaoImpl;

public class MongoArticleFactory implements ArticleDaoFactory {

	private final ArticleDao articleDao;

	private final ContentDao contentDao;

	public MongoArticleFactory(MongoConnection connection) {

		contentDao = new ContentDaoImpl(connection);
		articleDao = new ArticleDaoImpl(connection);
	}

	@Override
	public ContentDao getContentDao() {

		return contentDao;
	}

	@Override
	public ArticleDao getArticleDao() {

		return articleDao;
	}

}
