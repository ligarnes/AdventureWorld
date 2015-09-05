package net.alteiar.db.installer;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;

import net.alteiar.db.installer.exception.DbScriptException;
import net.alteiar.db.installer.exception.ParsingException;
import net.alteiar.db.installer.xml.SqlScriptWrapper;
import net.alteiar.sql.SqlModule;
import net.alteiar.sql.SqlScript;

public class PackageModule implements Module {

	private final SqlModule sqlModule;

	private final Map<String, SqlScript> scripts;

	public PackageModule(SqlModule sqlModule) {
		super();
		this.sqlModule = sqlModule;

		this.scripts = new HashMap<String, SqlScript>();
	}

	@Override
	public String getName() {

		return this.sqlModule.getName();
	}

	@Override
	public final void loadScripts() throws DbScriptException {

		for (String scriptName : sqlModule.getScript()) {

			try {
				SqlScript loaded = loadScript(scriptName);

				scripts.put(loaded.getName(), loaded);
			} catch (Exception e) {

				throw new DbScriptException(String.format("Fail to load the script %s", scriptName), e);
			}
		}
	}

	protected SqlScript loadScript(String name) throws ParsingException {

		LoggerFactory.getLogger(getClass()).debug("load script in package {}, with name {}", sqlModule.getPackage(),
				name);

		String packageModule = sqlModule.getPackage().replaceAll("\\.", "/");
		String filename = "/" + packageModule + "/" + name + ".xml";
		InputStream input = getClass().getResourceAsStream(filename);

		if (input == null) {

			throw new IllegalStateException("file not found " + filename);
		}

		SqlScriptWrapper wrapper = new SqlScriptWrapper();

		wrapper.load(input);

		return wrapper.getSqlScript();
	}

	@Override
	public SqlScript getScript(String name) {
		return scripts.get(name);
	}

	public List<String> getScriptNameList() {

		return sqlModule.getScript();
	}

	protected SqlModule getSqlModule() {
		return sqlModule;
	}

	@Override
	public Iterator<SqlScript> iterator() {

		return new SqlScriptIterator(this);
	}
}
