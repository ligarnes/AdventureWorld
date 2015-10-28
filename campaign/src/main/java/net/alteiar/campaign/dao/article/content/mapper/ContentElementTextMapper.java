package net.alteiar.campaign.dao.article.content.mapper;

import com.mongodb.DBObject;

import net.alteiar.campaign.article.content.Content;
import net.alteiar.campaign.article.content.ContentElementText;
import net.alteiar.campaign.article.content.ContentText;

class ContentElementTextMapper extends ContentMapper {

	private static final String PROPERTY_KEY = "key";
	private static final String PROPERTY_TEXT = "text";

	@Override
	public ContentElementText parse(DBObject dbObject) {

		ContentElementText content = null;

		if (dbObject != null) {

			content = new ContentElementText();
			load(dbObject, content);

			content.setKey((String) dbObject.get(PROPERTY_KEY));
			content.setText((String) dbObject.get(PROPERTY_TEXT));
		}

		return content;
	}

	@Override
	public DBObject generate(Content content) {

		DBObject doc = null;

		if (content instanceof ContentText) {

			ContentElementText contentText = (ContentElementText) content;

			doc = createDbObject(contentText);

			doc.put(PROPERTY_KEY, contentText.getKey());
			doc.put(PROPERTY_TEXT, contentText.getText());
		}

		return doc;
	}
}
