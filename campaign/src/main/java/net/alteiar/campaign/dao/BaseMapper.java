package net.alteiar.campaign.dao;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import net.alteiar.campaign.article.BaseObject;

public class BaseMapper {

	private static final String PROPERTY_ID = "_id";

	private ObjectId getObjectId(BaseObject obj) {

		ObjectId id = null;

		if (obj.getId() != null) {

			id = new ObjectId(obj.getId());
		} else {

			id = new ObjectId();
			obj.setId(id.toStringMongod());
		}

		return id;
	}

	protected BasicDBObject createDbObject(BaseObject object) {

		BasicDBObject doc = new BasicDBObject();

		doc.put(PROPERTY_ID, getObjectId(object));

		return doc;
	}

	protected void load(DBObject dbObject, BaseObject toLoad) {

		ObjectId id = (ObjectId) dbObject.get(PROPERTY_ID);
		toLoad.setId(id.toStringMongod());
	}

	public DBObject createDbObjectIdOnly(BaseObject obj) {

		return createDbObjectIdOnly(obj.getId());
	}

	public DBObject createDbObjectIdOnly(String id) {

		return new BasicDBObject().append(PROPERTY_ID, new ObjectId(id));
	}

}
