package net.alteiar.campaign.article.content;

import java.util.ArrayList;
import java.util.List;

public class ContentSection extends BaseContent implements Content {

	private String title;

	private final List<String> contents;

	public ContentSection() {

		contents = new ArrayList<>();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public List<String> getContentIds() {
		return contents;
	}
}
