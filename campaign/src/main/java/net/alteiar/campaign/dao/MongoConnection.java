package net.alteiar.campaign.dao;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class MongoConnection {

	private MongoClient mongoClient;

	public void initialize() throws UnknownHostException {

		mongoClient = new MongoClient("localhost", 27017);

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			shutdown();
		}));
	}

	public MongoClient getClient() {
		return mongoClient;
	}

	public DB getDatabase() {
		return mongoClient.getDB("adventureWorld");
	}

	private void shutdown() {

		mongoClient.close();
	}
}
