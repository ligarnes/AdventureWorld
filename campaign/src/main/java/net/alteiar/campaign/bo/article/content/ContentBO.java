package net.alteiar.campaign.bo.article.content;

import java.util.List;

import net.alteiar.campaign.article.content.Content;

public interface ContentBO {

	String getId();

	void create();

	void delete();

	void addContent(ContentBO content);

	void removeContent(ContentBO content);

	void setPublic(boolean isPublic);

	List<ContentBO> getChildrenContent();

	Content getContentData();

	boolean isPublic();

}
