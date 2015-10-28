package net.alteiar.campaign.bo.article.content;

import java.util.List;

import net.alteiar.campaign.article.content.Content;
import net.alteiar.campaign.dao.article.content.ContentDao;

public class ContentBOImpl implements ContentBO {

	private final ContentBOFactory factory;
	private final ContentDao contentDao;

	private final Content content;
	private List<ContentBO> childrenContent;

	protected ContentBOImpl(ContentBOFactory factory, ContentDao contentDao, String contentId) {

		this.factory = factory;
		this.contentDao = contentDao;
		this.content = contentDao.findById(contentId);
		childrenContent = null;
	}

	protected ContentBOImpl(ContentBOFactory factory, ContentDao contentDao, Content content) {

		this.factory = factory;
		this.contentDao = contentDao;
		this.content = content;
		childrenContent = null;
	}

	@Override
	public Content getContentData() {

		return content;
	}

	private void update() {

		contentDao.update(content);
	}

	@Override
	public void create() {

		contentDao.insert(content);
	}

	@Override
	public void delete() {

		contentDao.delete(content.getId());
	}

	@Override
	public void setPublic(boolean isPublic) {

		content.setPublic(isPublic);
		update();
	}

	@Override
	public void addContent(ContentBO content) {

		content.create();

		this.getContentData().getContentIds().add(content.getId());
	}

	@Override
	public void removeContent(ContentBO content) {

	}

	@Override
	public List<ContentBO> getChildrenContent() {

		if (childrenContent == null) {

			childrenContent = factory.getContent(content.getContentIds());
		}
		return childrenContent;
	}

	@Override
	public String getId() {

		return content.getId();
	}

	@Override
	public boolean isPublic() {

		return content.isPublic();
	}

}
