package net.alteiar.campaign.dao.article.content.mapper;

import com.mongodb.DBObject;

import net.alteiar.campaign.article.content.Content;
import net.alteiar.campaign.article.content.ContentText;

class ContentTextMapper extends ContentMapper {

	private static final String PROPERTY_TEXT = "text";

	@Override
	public ContentText parse(DBObject dbObject) {

		ContentText content = null;

		if (dbObject != null) {

			content = new ContentText();
			load(dbObject, content);

			content.setText((String) dbObject.get(PROPERTY_TEXT));
		}

		return content;
	}

	@Override
	public DBObject generate(Content content) {

		DBObject doc = null;

		if (content instanceof ContentText) {

			ContentText contentText = (ContentText) content;

			doc = createDbObject(contentText);
			doc.put(PROPERTY_TEXT, contentText.getText());
		}

		return doc;
	}
}
