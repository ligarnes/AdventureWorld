package net.alteiar.campaign.view.model;

import java.util.HashMap;
import java.util.Map;

public class Request {

	private Map<String, Object> parameters;

	public Request() {

		parameters = new HashMap<>();
	}

	public void put(String key, Object value) {

		parameters.put(key, value);
	}

	public Object getValue(String key) {

		return parameters.get(key);
	}

	public Object getValue(String key, Object defaultValue) {

		return parameters.getOrDefault(key, defaultValue);
	}
}
