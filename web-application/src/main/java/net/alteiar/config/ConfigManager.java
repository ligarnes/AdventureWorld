package net.alteiar.config;

import java.util.Properties;

public class ConfigManager {

	private static volatile ConfigManager INSTANCE;

	public static ConfigManager getInstance() {

		if (INSTANCE == null) {

			synchronized (ConfigManager.class) {

				if (INSTANCE == null) {

					INSTANCE = new ConfigManager();
				}

			}
		}

		return INSTANCE;
	}

	private final Properties configuration;

	public ConfigManager() {
		configuration = new Properties();
	}

	public void load() {

	}

	public void setProperty(String key, String value) {

		this.configuration.put(key, value);
	}

	public String getProperty(String key, String defaultValue) {

		return this.configuration.getProperty(key, defaultValue);
	}
}
