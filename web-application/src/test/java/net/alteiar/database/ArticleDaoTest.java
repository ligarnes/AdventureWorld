package net.alteiar.database;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import junit.framework.Assert;
import net.alteiar.dao.campaign.content.ArticleDao;
import net.alteiar.dao.campaign.content.ArticleDaoImpl;
import net.alteiar.model.content.Article;

public class ArticleDaoTest {

	@BeforeClass
	public static void setup() {

		DatabaseUtil.start();
	}

	private ArticleDao dao;

	@Before
	public void before() {

		DatabaseUtil.reset();

		dao = new ArticleDaoImpl();
		dao.setDatasource(DatabaseUtil.getDatasource());
	}

	@After
	public void after() {

		DatabaseUtil.reset();
		dao = null;
	}

	@Test
	public void testCreateArticle() {

		String title = "Title";
		String htmlContent = "<p>content<br/> test</p>";

		Article article = new Article();
		article.setTitle(title);
		article.setContent(htmlContent);

		dao.insert(article);

		Article found = dao.find(article.getId());

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

		dao.insert(article);

		Article found = dao.find(article.getId());

		Assert.assertEquals(title, found.getTitle());
		Assert.assertEquals(htmlContent, found.getContent());

		dao.delete(article.getId());

		dao.find(article.getId());
	}

	@Test
	public void testUpdateArticle() {

		String title = "Title";
		String htmlContent = "<p>content<br/> test</p>";

		Article article = new Article();
		article.setTitle(title);
		article.setContent(htmlContent);

		dao.insert(article);

		Article found = dao.find(article.getId());

		Assert.assertEquals(title, found.getTitle());
		Assert.assertEquals(htmlContent, found.getContent());

		String anotherContent = "<p>test <b>bold</b> fin du paragraphe</p>";
		article.setContent(anotherContent);

		dao.update(article);

		found = dao.find(article.getId());

		Assert.assertEquals(title, found.getTitle());
		Assert.assertEquals(anotherContent, found.getContent());
	}
}
