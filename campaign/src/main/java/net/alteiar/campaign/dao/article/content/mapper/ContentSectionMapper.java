package net.alteiar.campaign.dao.article.content.mapper;

import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import net.alteiar.campaign.article.content.Content;
import net.alteiar.campaign.article.content.ContentSection;

class ContentSectionMapper extends ContentMapper {

	private static final String PROPERTY_TITLE = "titre";
	private static final String PROPERTY_CONTENT_ID = "list_content_id";

	protected ContentSectionMapper() {
	}

	@Override
	public DBObject generate(Content content) {

		BasicDBObject doc = null;

		if (content instanceof ContentSection) {

			ContentSection section = (ContentSection) content;

			doc = createDbObject(section);

			doc.put(PROPERTY_TITLE, section.getTitle());
			doc.put(PROPERTY_CONTENT_ID, section.getContentIds());
		}

		return doc;
	}

	@Override
	public ContentSection parse(DBObject dbObject) {

		ContentSection section = null;

		if (dbObject != null) {

			section = new ContentSection();
			load(dbObject, section);
			section.setTitle((String) dbObject.get(PROPERTY_TITLE));

			section.getContentIds().addAll((List<String>) dbObject.get(PROPERTY_CONTENT_ID));
		}

		return section;
	}

}
