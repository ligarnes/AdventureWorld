package net.alteiar.context;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcConnectionPool;

import net.alteiar.config.ConfigManager;

public class H2PersistenceAdapterImpl implements PersistenceAdapter {

	private static final String PROPERTY_JDBC_URL = "jdbc.url";
	private static final String PROPERTY_JDBC_USERNAME = "jdbc.username";
	private static final String PROPERTY_JDBC_PASSWORD = "jdbc.password";

	private static final String PROPERTY_JDBC_URL_DEFAULT = "jdbc:h2:file:~/database/test";
	private static final String PROPERTY_JDBC_USERNAME_DEFAULT = "sa";
	private static final String PROPERTY_JDBC_PASSWORD_DEFAULT = "";

	private volatile DataSource ds;

	@Override
	public void initialize() {

		if (ds == null) {

			synchronized (getClass()) {

				if (ds == null) {

					String url = ConfigManager.getInstance().getProperty(PROPERTY_JDBC_URL, PROPERTY_JDBC_URL_DEFAULT);
					String username = ConfigManager.getInstance().getProperty(PROPERTY_JDBC_USERNAME,
							PROPERTY_JDBC_USERNAME_DEFAULT);
					String password = ConfigManager.getInstance().getProperty(PROPERTY_JDBC_PASSWORD,
							PROPERTY_JDBC_PASSWORD_DEFAULT);

					JdbcConnectionPool cp = JdbcConnectionPool.create(url, username, password);

					cp.setMaxConnections(25);
					cp.setLoginTimeout(30);

					this.ds = cp;
				}
			}
		}
	}

	@Override
	public void shutdown() {

		if (ds instanceof JdbcConnectionPool) {

			((JdbcConnectionPool) ds).dispose();
		}
	}

	@Override
	public DataSource getDatasource() {

		return ds;
	}
}
