package net.alteiar.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.alteiar.db.installer.exception.ParsingException;

public class AppContext {

	private static final Logger logger = LoggerFactory.getLogger(AppContext.class);

	private static volatile AppContext INSTANCE = new AppContext();

	public static AppContext getInstance() {

		if (INSTANCE == null) {

			synchronized (AppContext.class) {

				if (INSTANCE == null) {

					setInstance(new AppContext());
				}
			}
		}

		return INSTANCE;
	}

	public static void setInstance(AppContext context) {

		INSTANCE = context;
	}

	private final PersistenceAdapter persistenceAdapter;

	private final DatabaseInstaller dbInstaller;

	private AppContext() {

		persistenceAdapter = new PersistenceAdapter();
		dbInstaller = new DatabaseInstaller();
	}

	public void initialize() {

		persistenceAdapter.initialize();

		try {

			dbInstaller.addDatabaseInstaller("/database/installer/module.xml");
		} catch (ParsingException e) {

			logger.error("Fail to parse the database module", e);
		}

		dbInstaller.install();
	}

	public void start() {

	}

	public void stop() {

		persistenceAdapter.shutdown();
	}

	public PersistenceAdapter getPersistenceAdapter() {

		return persistenceAdapter;
	}
}
