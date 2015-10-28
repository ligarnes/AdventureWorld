package net.alteiar.campaign.dao.article;

import net.alteiar.campaign.article.Article;

public interface ArticleDao {

	void insert(Article content);

	void update(Article content);

	void delete(Article content);

	void delete(String contentId);

	Article findById(String contentId);
}
