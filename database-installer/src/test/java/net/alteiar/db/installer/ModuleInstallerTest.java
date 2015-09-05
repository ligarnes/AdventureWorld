package net.alteiar.db.installer;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import net.alteiar.db.installer.exception.DbScriptException;
import net.alteiar.sql.SqlModule;

public class ModuleInstallerTest {

	private JdbcDataSource ds;

	@Before
	public void before() throws Exception {

		Class.forName("org.h2.Driver");

		ds = new JdbcDataSource();
		ds.setURL("jdbc:h2:./test");
		ds.setUser("sa");
		ds.setPassword("sa");

		// drop all
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		jdbcTemplate.execute("DROP ALL OBJECTS DELETE FILES");
	}

	@After
	public void after() {

		// drop all
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		jdbcTemplate.execute("DROP ALL OBJECTS DELETE FILES");
	}

	@Test
	public void execute() throws Exception {

		SqlModule sqlModule = new SqlModule();
		sqlModule.setName("MyTestModule");
		sqlModule.setDescription("Description de mon module");
		sqlModule.setPackage("xml.script");
		sqlModule.getScript().add("create db");
		sqlModule.getScript().add("create db1");
		sqlModule.getScript().add("create db2");
		sqlModule.getScript().add("create db3");

		PackageModule module = new PackageModule(sqlModule);

		ModuleInstaller moduleInstaller = new ModuleInstaller(module);
		moduleInstaller.setDatasource(ds);

		moduleInstaller.execute();

		// make a select
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		int count = jdbcTemplate.queryForObject("select count(*) from test", Integer.class);

		Assert.assertEquals(3, count);

		count = jdbcTemplate.queryForObject("select count(*) from test where name = 'aervia'", Integer.class);
		Assert.assertEquals(1, count);
	}

	@Test(expected = DbScriptException.class)
	public void executeWithException() throws Exception {

		SqlModule sqlModule = new SqlModule();
		sqlModule.setName("MyTestModule");
		sqlModule.setDescription("Description de mon module");
		sqlModule.setPackage("/xml/script");
		sqlModule.getScript().add("create db");
		sqlModule.getScript().add("invalid script");

		PackageModule packageModule = new PackageModule(sqlModule);

		Assert.assertEquals("MyTestModule", packageModule.getName());

		ModuleInstaller moduleInstaller = new ModuleInstaller(packageModule);
		moduleInstaller.setDatasource(ds);

		moduleInstaller.execute();
	}
}
