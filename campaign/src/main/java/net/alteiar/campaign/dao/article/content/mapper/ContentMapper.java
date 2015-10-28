package net.alteiar.campaign.dao.article.content.mapper;

import com.mongodb.DBObject;

import net.alteiar.campaign.article.BaseObject;
import net.alteiar.campaign.article.content.Content;
import net.alteiar.campaign.dao.BaseMapper;

public abstract class ContentMapper extends BaseMapper {

	private static final String PROPERTY_CONTENT_TYPE = "content_type";
	private static final String PROPERTY_IS_PUBLIC = "is_public";

	protected static String getContentType(Content content) {

		return content.getClass().getSimpleName();
	}

	protected static String getContentType(DBObject object) {

		return (String) object.get(ContentMapper.PROPERTY_CONTENT_TYPE);
	}

	public DBObject generate(Content content) {

		DBObject doc = super.createDbObject(content);
		doc.put(PROPERTY_CONTENT_TYPE, getContentType(content));
		doc.put(PROPERTY_IS_PUBLIC, content.isPublic());

		return doc;
	}

	public abstract Content parse(DBObject dbObject);

	@Override
	protected void load(DBObject dbObject, BaseObject toLoad) {

		super.load(dbObject, toLoad);

		if (toLoad instanceof Content) {
			Content obj = (Content) toLoad;

			obj.setPublic((Boolean) dbObject.get(PROPERTY_IS_PUBLIC));
		} else {

			throw new IllegalStateException("The base object is not a Content");
		}
	}
}
