package net.alteiar.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.alteiar.context.AppContext;
import net.alteiar.model.Access;
import net.alteiar.model.content.Article;
import net.alteiar.model.content.Topic;

@Controller
@RequestMapping(value = "/article/")
public class ArticleController extends AbstractController {

	private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/{articleId}", method = RequestMethod.GET)
	public String viewArticle(@PathVariable Integer articleId, Model model) {

		logger.info("Get the article with id {}.", articleId);

		Article article = null;
		try {

			article = AppContext.getInstance().getDaoFactory().getArticleDao().find(articleId);
		} catch (DataAccessException ex) {

			article = new Article();
			article.setTitle("L'article n'existe pas");
			article.setContent("");
		}

		model.addAttribute("article", article);

		return "internal/article/article_view";
	}

	@RequestMapping(value = "/create")
	public String createArticle(Model model) {

		return "internal/article/article_create";
	}

	@RequestMapping(value = "/createFinal", method = RequestMethod.POST)
	public String createArticleRequest(Model model, @RequestParam(value = "title") String title,
			@RequestParam(value = "content") String content) {

		// create access
		Access access = new Access();
		access.setPublic(true);

		AppContext.getInstance().getDaoFactory().getAccessDao().insert(access);

		// create article
		Article article = new Article();
		article.setTitle(title);
		article.setContent(content);

		AppContext.getInstance().getDaoFactory().getArticleDao().insert(article);

		// create Topic
		Topic topic = new Topic();
		topic.setName(title);
		// topic.setParentId(parentId);
		topic.setAccessId(access.getId());
		topic.setContentId(article.getId());
		topic.setContentType(article.getClass().getCanonicalName());

		AppContext.getInstance().getDaoFactory().getTopicDao().insert(topic);

		model.addAttribute("article", article);

		return "internal/article/article_view";
	}

	@Override
	@ExceptionHandler(Exception.class)
	public String handleException(Exception ex) {
		return handleAllException(ex);
	}
}
