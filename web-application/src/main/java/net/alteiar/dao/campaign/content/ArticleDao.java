package net.alteiar.dao.campaign.content;

import java.util.List;

import net.alteiar.dao.Dao;
import net.alteiar.model.content.Article;

public interface ArticleDao extends Dao {

	void insert(Article article);

	void update(Article article);

	List<Article> findAll();

	Article find(long id);

	void delete(long id);
}
