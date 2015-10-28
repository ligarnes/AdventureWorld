package net.alteiar.campaign.dao.article.content;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;

import net.alteiar.campaign.article.content.BaseContent;
import net.alteiar.campaign.article.content.Content;
import net.alteiar.campaign.dao.MongoBaseDao;
import net.alteiar.campaign.dao.MongoConnection;
import net.alteiar.campaign.dao.article.content.mapper.ContentMapper;
import net.alteiar.campaign.dao.article.content.mapper.ContentMapperFactory;

public class ContentDaoImpl extends MongoBaseDao implements ContentDao {

	private static final Logger logger = LogManager.getLogger(ContentDaoImpl.class);

	private static final String COLLECTION_NAME = "Content";

	private static final String PROPERTY_ID = "_id";

	private DBCollection getCollection() {

		return getDatabase().getCollection(COLLECTION_NAME);
	}

	private final ContentMapperFactory mapperFactory;

	public ContentDaoImpl(MongoConnection connection) {
		super(connection);
		mapperFactory = new ContentMapperFactory();
	}

	@Override
	public void insert(Content content) {

		ObjectId id = new ObjectId();

		ContentMapper mapper = mapperFactory.getMapper(content);

		DBObject doc = mapper.generate(content);
		doc.put(PROPERTY_ID, id);

		getCollection().insert(doc);

		((BaseContent) content).setId(id.toStringMongod());
	}

	@Override
	public void update(Content content) {

		ContentMapper mapper = mapperFactory.getMapper(content);

		DBObject doc = mapper.generate(content);

		getCollection().update(createDbObjectIdOnly(content), doc);
	}

	@Override
	public void delete(Content content) {

		delete(content.getId());
	}

	@Override
	public void delete(String contentId) {

		getCollection().remove(createDbObjectIdOnly(contentId));
	}

	@Override
	public Content findById(String contentId) {

		DBObject found = getCollection().findOne(createDbObjectIdOnly(contentId));

		ContentMapper mapper = mapperFactory.getMapper(found);

		return mapper.parse(found);
	}

	private DBObject createDbObjectIdOnly(Content content) {

		return createDbObjectIdOnly(content.getId());
	}

	private DBObject createDbObjectIdOnly(String contentId) {

		return new BasicDBObject().append(PROPERTY_ID, new ObjectId(contentId));
	}

	@Override
	public List<Content> findAll(List<String> contentIds) {

		ObjectId[] ids = new ObjectId[contentIds.size()];

		for (int i = 0; i < ids.length; i++) {

			ids[i] = new ObjectId(contentIds.get(i));
		}

		DBObject query = QueryBuilder.start("_id").in(ids).get();

		logger.trace("Mongodb query: {}", query);

		DBCursor foundCursor = getCollection().find(query);

		List<Content> content = new ArrayList<>();
		for (DBObject dbObject : foundCursor) {

			ContentMapper mapper = mapperFactory.getMapper(dbObject);
			content.add(mapper.parse(dbObject));
		}

		return content;
	}

}
