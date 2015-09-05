package net.alteiar.context;

import java.io.InputStream;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.alteiar.db.installer.ModuleInstaller;
import net.alteiar.db.installer.SqlModuleInstallerBuilder;
import net.alteiar.db.installer.exception.DbScriptException;
import net.alteiar.db.installer.exception.ParsingException;

public class DatabaseInstaller {

	private static final Logger logger = LoggerFactory.getLogger(DatabaseInstaller.class);

	private final ArrayList<ModuleInstaller> dbInstaller;

	public DatabaseInstaller() {

		dbInstaller = new ArrayList<>();
	}

	public void addDatabaseInstaller(String url) throws ParsingException {

		ModuleInstaller installer = SqlModuleInstallerBuilder.create(url);
		installer.setDatasource(AppContext.getInstance().getPersistenceAdapter().getDatasource());
		dbInstaller.add(installer);
	}

	public void addDatabaseInstaller(InputStream file) throws ParsingException {

		ModuleInstaller installer = SqlModuleInstallerBuilder.create(file);
		installer.setDatasource(AppContext.getInstance().getPersistenceAdapter().getDatasource());
		dbInstaller.add(installer);
	}

	public void install() {

		for (ModuleInstaller moduleInstaller : dbInstaller) {

			try {
				moduleInstaller.execute();
			} catch (DbScriptException e) {

				logger.warn("Fail to execute the database installer module", e);
			}
		}
	}
}
