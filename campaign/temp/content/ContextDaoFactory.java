package net.alteiar.campaign.dao.mysql.article.content;

public class ContextDaoFactory {

	private static final ContextDaoFactory INSTANCE = new ContextDaoFactory();

	public static ContextDaoFactory getInstance() {

		return INSTANCE;
	}

	private final ContentTextDao contentTextDao;

	private final ContentSectionDao contentSectionDao;

	private ContextDaoFactory() {

		contentTextDao = new ContentTextDaoImpl();
		contentSectionDao = new ContentSectionDaoImpl();
	}

	public ContentTextDao getContentTextDao() {

		return contentTextDao;
	}

	public ContentSectionDao getContentSectionDao() {

		return contentSectionDao;
	}
}
