package net.alteiar.model;

import java.util.ArrayList;
import java.util.List;

public class Campaign {

	private long id;

	private String name;

	private List<Long> contentIds;

	public Campaign() {
		contentIds = new ArrayList<>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Long> getContent() {
		return contentIds;
	}

	public void setContent(List<Long> content) {
		this.contentIds = content;
	}

}
