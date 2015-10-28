package net.alteiar.campaign.article;

import java.util.ArrayList;
import java.util.List;

public class Article extends BaseObjectImpl {

	private Long parentId;

	private String name;

	private boolean isPublic;

	private boolean isCategory;

	private final List<String> contentIds;

	public Article() {

		contentIds = new ArrayList<>();
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public boolean isCategory() {
		return isCategory;
	}

	public void setCategory(boolean isCategory) {
		this.isCategory = isCategory;
	}

	public boolean isArticle() {
		return !isCategory;
	}

	public List<String> getContentIds() {
		return contentIds;
	}
}
