package net.alteiar.campaign.dao.article;

import net.alteiar.campaign.dao.article.content.ContentDao;

public interface ArticleDaoFactory {

	ContentDao getContentDao();

	ArticleDao getArticleDao();
}
