package net.alteiar.campaign.dao.mysql.article.content;

import net.alteiar.campaign.article.content.BaseContent;
import net.alteiar.dao.Dao;

public interface ContentDao<E extends BaseContent> extends Dao {

	void insert(E content);

	void update(E content);

	void delete(E content);
}
