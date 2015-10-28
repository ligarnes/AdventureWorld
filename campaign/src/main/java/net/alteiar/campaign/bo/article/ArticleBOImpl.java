package net.alteiar.campaign.bo.article;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.alteiar.campaign.article.Article;
import net.alteiar.campaign.bo.article.content.ContentBO;
import net.alteiar.campaign.bo.article.content.ContentBOFactory;
import net.alteiar.campaign.dao.article.ArticleDao;

public class ArticleBOImpl implements ArticleBO {

	private static final Logger logger = LogManager.getLogger(ArticleBOImpl.class);

	private final ArticleDao articleDao;

	private final ContentBOFactory contentFactory;

	private final Article article;
	public List<ContentBO> content;

	protected ArticleBOImpl(ContentBOFactory contentFactory, ArticleDao dao, String articleId) {

		this.articleDao = dao;
		this.contentFactory = contentFactory;
		this.article = articleDao.findById(articleId);
	}

	protected ArticleBOImpl(ContentBOFactory contentFactory, ArticleDao dao, Article article) {

		this.articleDao = dao;
		this.contentFactory = contentFactory;
		this.article = article;
	}

	private synchronized void loadContent() {

		// lazy load the content
		if (content == null) {

			logger.trace("load content of {} {}", getName(), article.getContentIds());
			content = contentFactory.getContent(article.getContentIds());
		}
	}

	protected Article getArticle() {

		return article;
	}

	private void updateDTO() {

		articleDao.update(getArticle());
	}

	@Override
	public void create() {

		articleDao.insert(article);
	}

	@Override
	public String getName() {

		return getArticle().getName();
	}

	@Override
	public List<ContentBO> getContent() {

		loadContent();
		return content;
	}

	@Override
	public void setName(String title) {

		article.setName(title);
		updateDTO();
	}

	@Override
	public void setPublic(boolean isPublic) {

		article.setPublic(isPublic);
		updateDTO();
	}

	@Override
	public void addContent(ContentBO content) {

		content.create();
		getArticle().getContentIds().add(content.getId());
		updateDTO();
	}

	@Override
	public void removeContent(ContentBO content) {

		this.article.getContentIds().remove(content);
		updateDTO();
		content.delete();
	}

	@Override
	public void delete() {

		for (ContentBO contentBO : getContent()) {
			contentBO.delete();
		}

		this.delete();
	}

	@Override
	public boolean isPublic() {

		return getArticle().isArticle();
	}

}
