package net.alteiar.dao;

import javax.sql.DataSource;

import net.alteiar.context.AppContext;
import net.alteiar.dao.campaign.AccessDao;
import net.alteiar.dao.campaign.AccessDaoImpl;
import net.alteiar.dao.campaign.PlayerDao;
import net.alteiar.dao.campaign.PlayerDaoImpl;
import net.alteiar.dao.campaign.content.ArticleDao;
import net.alteiar.dao.campaign.content.ArticleDaoImpl;
import net.alteiar.dao.campaign.content.TopicDao;
import net.alteiar.dao.campaign.content.TopicDaoImpl;

public class DaoFactory {

	private PlayerDao userDao;

	private ArticleDao articleDao;
	private AccessDao accessDao;

	private TopicDao topicDao;

	public void initialize() {

		final DataSource ds = AppContext.getInstance().getPersistenceAdapter().getDatasource();

		DaoBuilder<PlayerDao> builderUserDao = new DaoBuilder<PlayerDao>() {

			@Override
			protected PlayerDao create() {
				return new PlayerDaoImpl();
			}
		};
		userDao = builderUserDao.build(ds);

		DaoBuilder<AccessDao> builderAccess = new DaoBuilder<AccessDao>() {

			@Override
			protected AccessDao create() {
				return new AccessDaoImpl();
			}
		};
		accessDao = builderAccess.build(ds);

		DaoBuilder<ArticleDao> builderArticleDao = new DaoBuilder<ArticleDao>() {

			@Override
			protected ArticleDao create() {
				return new ArticleDaoImpl();
			}
		};
		articleDao = builderArticleDao.build(ds);

		DaoBuilder<TopicDao> builderTopicDao = new DaoBuilder<TopicDao>() {

			@Override
			protected TopicDao create() {
				return new TopicDaoImpl();
			}
		};
		topicDao = builderTopicDao.build(ds);
	}

	public PlayerDao getPlayerDao() {

		return userDao;
	}

	public AccessDao getAccessDao() {

		return accessDao;
	}

	public ArticleDao getArticleDao() {

		return articleDao;
	}

	public TopicDao getTopicDao() {

		return topicDao;
	}
}
