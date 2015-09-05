package net.alteiar.database;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import net.alteiar.config.ConfigManager;
import net.alteiar.context.AppContext;

public class DatabaseUtil {

	public static void start() {

		ConfigManager.getInstance().setProperty("jdbc.url", "jdbc:h2:file:./database/test");
		ConfigManager.getInstance().setProperty("jdbc.username", "sa");
		ConfigManager.getInstance().setProperty("jdbc.password", "");
	}

	public static void reset() {

		AppContext.getInstance().getPersistenceAdapter().initialize();
		DataSource ds = AppContext.getInstance().getPersistenceAdapter().getDatasource();

		// drop all
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		jdbcTemplate.execute("DROP ALL OBJECTS DELETE FILES");

		AppContext.getInstance().initialize();
	}

	public static void shutdown() {

		// AppContext.getInstance().stop();
	}
}
