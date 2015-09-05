package net.alteiar.db.installer;

import java.io.InputStream;

import net.alteiar.db.installer.exception.ParsingException;
import net.alteiar.db.installer.xml.SqlModuleWrapper;

public class SqlModuleInstallerBuilder {

	public static ModuleInstaller create(String input) throws ParsingException {

		InputStream inputStream = SqlModuleInstallerBuilder.class.getResourceAsStream(input);
		if (inputStream == null) {
			throw new ParsingException(String.format("The input does not exist %s", input));
		}

		return create(inputStream);
	}

	public static ModuleInstaller create(InputStream input) throws ParsingException {

		SqlModuleWrapper wrapper = new SqlModuleWrapper();
		wrapper.load(input);

		PackageModule module = new PackageModule(wrapper.getSqlModule());

		return new ModuleInstaller(module);
	}
}
