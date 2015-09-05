package net.alteiar.db.installer;

import javax.sql.DataSource;

import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import net.alteiar.db.installer.dao.DbInstallerDao;
import net.alteiar.db.installer.exception.DbScriptException;
import net.alteiar.sql.SqlScript;

public class ModuleInstaller {

	private final Module module;

	private final DbInstallerDao dao;

	public ModuleInstaller(Module module) {

		this.module = module;
		this.dao = new DbInstallerDao();
	}

	public void setDatasource(DataSource ds) {

		dao.setDataSource(ds);
	}

	@Transactional
	private void executeScript(SqlScript script) throws DbScriptException {

		String moduleName = module.getName();
		String scriptName = script.getName();

		try {

			// execute the script
			LoggerFactory.getLogger(getClass()).info("Execute script {} in module {}", scriptName, moduleName);

			for (String query : script.getQuery()) {

				dao.execute(query);
			}
			dao.insertScript(moduleName, script.getName(), script.getDescription());

			LoggerFactory.getLogger(getClass()).info("Script {} in module {} executed with success", scriptName,
					moduleName);

		} catch (RuntimeException ex) {

			// failure in script execution
			throw new DbScriptException(
					String.format("Failure to apply script %s in module %s", scriptName, moduleName), ex);
		}
	}

	public void execute() throws DbScriptException {

		try {

			dao.createTable();
			LoggerFactory.getLogger(getClass()).info("Create db_installer table");
		} catch (RuntimeException ex) {

			LoggerFactory.getLogger(getClass()).info("The db_installer table is already created", ex);
		}

		String moduleName = module.getName();

		module.loadScripts();

		LoggerFactory.getLogger(getClass()).info("Execute scripts in module {}", moduleName);

		// execute all scripts of this module
		for (SqlScript script : this.module) {

			String scriptName = script.getName();

			// verify if script is already applied
			if (!dao.findScript(moduleName, scriptName)) {

				executeScript(script);
			} else {

				// script already executed
				LoggerFactory.getLogger(getClass()).info("The script {} in module {} is already applied", scriptName,
						moduleName);
			}
		}
	}
}
