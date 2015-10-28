package net.alteiar.campaign.dao;

import java.net.UnknownHostException;

import net.alteiar.campaign.dao.article.ArticleDaoFactory;
import net.alteiar.campaign.dao.article.MongoArticleFactory;

public class MongoDaoFactory implements DaoFactory {

	private final MongoConnection connection;

	public MongoDaoFactory() throws UnknownHostException {

		this.connection = new MongoConnection();
		this.connection.initialize();
	}

	@Override
	public ArticleDaoFactory createArticleFactory() {

		return new MongoArticleFactory(connection);
	}

}
