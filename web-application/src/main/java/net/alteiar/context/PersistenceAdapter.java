package net.alteiar.context;

import javax.sql.DataSource;

public interface PersistenceAdapter {

	void initialize();

	void shutdown();

	DataSource getDatasource();
}
