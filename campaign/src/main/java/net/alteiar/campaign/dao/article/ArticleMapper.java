package net.alteiar.campaign.dao.article;

import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import net.alteiar.campaign.article.Article;
import net.alteiar.campaign.dao.BaseMapper;

public class ArticleMapper extends BaseMapper {

	private static final String PROPERTY_NAME = "name";
	private static final String PROPERTY_PARENT_ID = "parend_id";

	private static final String PROPERTY_IS_PUBLIC = "is_public";
	private static final String PROPERTY_IS_CATEGORY = "is_category";
	private static final String PROPERTY_SECTION_ID = "list_section_id";

	protected ArticleMapper() {
	}

	public DBObject generate(Article article) {

		BasicDBObject doc = createDbObject(article);
		doc.put(PROPERTY_NAME, article.getName());
		doc.put(PROPERTY_PARENT_ID, article.getParentId());
		doc.put(PROPERTY_IS_PUBLIC, article.isPublic());
		doc.put(PROPERTY_IS_CATEGORY, article.isCategory());
		doc.put(PROPERTY_SECTION_ID, article.getContentIds());

		return doc;
	}

	public Article parse(DBObject dbObject) {

		Article article = null;

		if (dbObject != null) {

			article = new Article();
			load(dbObject, article);

			article.setName((String) dbObject.get(PROPERTY_NAME));
			article.setParentId((Long) dbObject.get(PROPERTY_PARENT_ID));

			article.setPublic((boolean) dbObject.get(PROPERTY_IS_PUBLIC));
			article.setCategory((boolean) dbObject.get(PROPERTY_IS_CATEGORY));

			article.getContentIds().addAll((List<String>) dbObject.get(PROPERTY_SECTION_ID));
		}

		return article;
	}
}
