package net.alteiar.campaign.dao.article.content;

import java.util.List;

import net.alteiar.campaign.article.content.Content;

public interface ContentDao {

	void insert(Content content);

	void update(Content content);

	void delete(Content content);

	void delete(String contentId);

	Content findById(String contentId);

	List<Content> findAll(List<String> contentIds);
}
