package net.alteiar.campaign.bo.article.content;

import java.util.ArrayList;
import java.util.List;

import net.alteiar.campaign.article.content.Content;
import net.alteiar.campaign.dao.article.ArticleDaoFactory;

public class ContentBOFactory {

	private final ArticleDaoFactory daoFactory;

	public ContentBOFactory(ArticleDaoFactory factory) {

		this.daoFactory = factory;
	}

	public ContentBO getContent(String contentId) {

		return new ContentBOImpl(this, daoFactory.getContentDao(), contentId);
	}

	public List<ContentBO> getContent(List<String> contentIds) {

		List<ContentBO> contentBoList = new ArrayList<>();
		List<Content> contents = daoFactory.getContentDao().findAll(contentIds);

		for (Content content : contents) {

			contentBoList.add(new ContentBOImpl(this, daoFactory.getContentDao(), content));
		}

		return contentBoList;
	}
}
