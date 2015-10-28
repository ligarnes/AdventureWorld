package net.alteiar.campaign.dao.article.content.mapper;

import java.util.HashMap;
import java.util.Map;

import com.mongodb.DBObject;

import net.alteiar.campaign.article.content.Content;
import net.alteiar.campaign.dao.exception.MapperNotFoundException;

abstract class AbstractContentParserFactory {

	private final Map<String, ContentMapper> mappers;

	protected AbstractContentParserFactory() {

		mappers = new HashMap<>();
	}

	protected void register(String contentType, ContentMapper mapper) {

		mappers.put(contentType, mapper);
	}

	protected abstract String getContentType(Content content);

	protected abstract String getContentType(DBObject object);

	public ContentMapper getMapper(DBObject object) {

		String contentType = getContentType(object);

		ContentMapper mapperFound = mappers.get(contentType);

		if (mapperFound == null) {

			throw new MapperNotFoundException(String.format("No mapper found for contentType [%s], supported are %s",
					contentType, mappers.keySet()));
		}

		return mapperFound;
	}

	public ContentMapper getMapper(Content content) {

		String contentType = getContentType(content);

		return mappers.get(contentType);
	}
}
