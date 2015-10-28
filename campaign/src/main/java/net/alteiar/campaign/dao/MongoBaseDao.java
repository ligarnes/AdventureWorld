package net.alteiar.campaign.dao;

import com.mongodb.DB;

public abstract class MongoBaseDao {

	private final MongoConnection conn;

	public MongoBaseDao(MongoConnection conn) {

		this.conn = conn;
	}

	protected DB getDatabase() {

		return conn.getDatabase();
	}
}
