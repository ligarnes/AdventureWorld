package net.alteiar.campaign.dao;

import net.alteiar.campaign.dao.article.ArticleDaoFactory;

public interface DaoFactory {

	ArticleDaoFactory createArticleFactory();
}
