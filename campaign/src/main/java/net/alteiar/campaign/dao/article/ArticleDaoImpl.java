package net.alteiar.campaign.dao.article;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import net.alteiar.campaign.article.Article;
import net.alteiar.campaign.dao.MongoBaseDao;
import net.alteiar.campaign.dao.MongoConnection;

public class ArticleDaoImpl extends MongoBaseDao implements ArticleDao {

	private static final String COLLECTION_NAME = "Article";

	private DBCollection getCollection() {

		return getDatabase().getCollection(COLLECTION_NAME);
	}

	private final ArticleMapper mapper;

	public ArticleDaoImpl(MongoConnection connection) {
		super(connection);
		mapper = new ArticleMapper();
	}

	@Override
	public void insert(Article article) {

		DBObject doc = mapper.generate(article);

		getCollection().insert(doc);
	}

	@Override
	public void update(Article content) {

		DBObject doc = mapper.generate(content);

		getCollection().update(mapper.createDbObjectIdOnly(content), doc);
	}

	@Override
	public void delete(Article content) {

		delete(content.getId());
	}

	@Override
	public void delete(String contentId) {

		getCollection().remove(mapper.createDbObjectIdOnly(contentId));
	}

	@Override
	public Article findById(String contentId) {

		DBObject found = getCollection().findOne(mapper.createDbObjectIdOnly(contentId));

		return mapper.parse(found);
	}

}
