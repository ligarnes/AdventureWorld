package net.alteiar.campaign.bo.article;

import java.util.List;

import net.alteiar.campaign.bo.article.content.ContentBO;

public interface ArticleBO {

	String getName();

	void setName(String title);

	boolean isPublic();

	void setPublic(boolean isPublic);

	void addContent(ContentBO content);

	void removeContent(ContentBO content);

	List<ContentBO> getContent();

	void delete();

	void create();
}
