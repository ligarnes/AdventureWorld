package net.alteiar.model.content;

public class Topic {

	private long id;

	private Long parentId;

	private String name;

	private long accessId;

	private String contentType;

	private Long contentId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public long getAccessId() {
		return accessId;
	}

	public void setAccessId(long accessId) {
		this.accessId = accessId;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Long getContentId() {
		return contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}
}
