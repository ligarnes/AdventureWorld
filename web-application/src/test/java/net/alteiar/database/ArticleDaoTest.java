package net.alteiar.database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import junit.framework.Assert;
import net.alteiar.dao.DaoFactory;
import net.alteiar.model.content.Article;

public class ArticleDaoTest {

	@Before
	public void setup() {

		DatabaseUtil.start();
		DatabaseUtil.reset();
	}

	@After
	public void after() {

		DatabaseUtil.shutdown();
	}

	@Test
	public void testCreateArticle() {

		String title = "Title";
		String htmlContent = "<p>content<br/> test</p>";

		Article article = new Article();
		article.setTitle(title);
		article.setContent(htmlContent);

		DaoFactory.getInstance().getArticleDao().insert(article);

		Article found = DaoFactory.getInstance().getArticleDao().find(article.getId());

		Assert.assertEquals(title, found.getTitle());
		Assert.assertEquals(htmlContent, found.getContent());
	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void testDeleteArticle() {

		String title = "Title";
		String htmlContent = "<p>content<br/> test</p>";

		Article article = new Article();
		article.setTitle(title);
		article.setContent(htmlContent);

		DaoFactory.getInstance().getArticleDao().insert(article);

		Article found = DaoFactory.getInstance().getArticleDao().find(article.getId());

		Assert.assertEquals(title, found.getTitle());
		Assert.assertEquals(htmlContent, found.getContent());

		DaoFactory.getInstance().getArticleDao().delete(article.getId());

		DaoFactory.getInstance().getArticleDao().find(article.getId());
	}

	@Test
	public void testUpdateArticle() {

		String title = "Title";
		String htmlContent = "<p>content<br/> test</p>";

		Article article = new Article();
		article.setTitle(title);
		article.setContent(htmlContent);

		DaoFactory.getInstance().getArticleDao().insert(article);

		Article found = DaoFactory.getInstance().getArticleDao().find(article.getId());

		Assert.assertEquals(title, found.getTitle());
		Assert.assertEquals(htmlContent, found.getContent());

		String anotherContent = "<p>test <b>bold</b> fin du paragraphe</p>";
		article.setContent(anotherContent);

		DaoFactory.getInstance().getArticleDao().update(article);

		found = DaoFactory.getInstance().getArticleDao().find(article.getId());

		Assert.assertEquals(title, found.getTitle());
		Assert.assertEquals(anotherContent, found.getContent());
	}
}
