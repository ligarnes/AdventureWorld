package net.alteiar.campaign.article.content;

import java.util.List;

import net.alteiar.campaign.article.BaseObject;

public interface Content extends BaseObject {

	boolean isPublic();

	void setPublic(boolean isPublic);

	List<String> getContentIds();
}
