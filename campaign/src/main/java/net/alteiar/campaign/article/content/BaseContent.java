package net.alteiar.campaign.article.content;

import java.util.Collections;
import java.util.List;

import net.alteiar.campaign.article.BaseObjectImpl;

public abstract class BaseContent extends BaseObjectImpl implements Content {

	private boolean isPublic;

	@Override
	public boolean isPublic() {
		return isPublic;
	}

	@Override
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	@Override
	public List<String> getContentIds() {

		return Collections.emptyList();
	}
}
