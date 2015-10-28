package net.alteiar.campaign.dao.article.content.mapper;

import com.mongodb.DBObject;

import net.alteiar.campaign.article.content.Content;
import net.alteiar.campaign.article.content.ContentElementText;
import net.alteiar.campaign.article.content.ContentSection;
import net.alteiar.campaign.article.content.ContentText;

public class ContentMapperFactory extends AbstractContentParserFactory {

	public ContentMapperFactory() {
		super();

		register(ContentText.class.getSimpleName(), new ContentTextMapper());
		register(ContentElementText.class.getSimpleName(), new ContentElementTextMapper());
		register(ContentSection.class.getSimpleName(), new ContentSectionMapper());
	}

	@Override
	public String getContentType(Content content) {

		return ContentMapper.getContentType(content);
	}

	@Override
	protected String getContentType(DBObject object) {

		return ContentMapper.getContentType(object);
	}
}
