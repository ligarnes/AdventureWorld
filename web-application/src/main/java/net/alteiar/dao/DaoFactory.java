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

	private static volatile DaoFactory INSTANCE;

	public static DaoFactory getInstance() {

		if (INSTANCE == null) {

			synchronized (DaoFactory.class) {

				if (INSTANCE == null) {

					setInstance(new DaoFactory());
				}
			}
		}

		return INSTANCE;
	}

	public static void setInstance(DaoFactory factory) {

		INSTANCE = factory;
	}

	private volatile PlayerDao userDao;

	private volatile ArticleDao articleDao;
	private volatile AccessDao accessDao;

	private volatile TopicDao categoryDao;

	private void initDao(Dao dao) {

		DataSource ds = AppContext.getInstance().getPersistenceAdapter().getDatasource();
		dao.setDatasource(ds);
	}

	public PlayerDao getPlayerDao() {

		if (userDao == null) {

			synchronized (this) {

				if (userDao == null) {

					userDao = new PlayerDaoImpl();
					initDao(userDao);
				}
			}
		}

		return userDao;
	}

	public AccessDao getAccessDao() {

		if (accessDao == null) {

			synchronized (this) {

				if (accessDao == null) {

					accessDao = new AccessDaoImpl();
					initDao(accessDao);
				}
			}
		}

		return accessDao;
	}

	public ArticleDao getArticleDao() {

		if (articleDao == null) {

			synchronized (this) {

				if (articleDao == null) {

					articleDao = new ArticleDaoImpl();
					initDao(articleDao);
				}
			}
		}

		return articleDao;
	}

	public TopicDao getTopicDao() {

		if (categoryDao == null) {

			synchronized (this) {

				if (categoryDao == null) {

					categoryDao = new TopicDaoImpl();
					initDao(categoryDao);
				}
			}
		}

		return categoryDao;
	}
}
