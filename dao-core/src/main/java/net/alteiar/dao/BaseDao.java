package net.alteiar.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class BaseDao implements Dao {

	private JdbcTemplate jdbcTemplate;

	/**
	 * Set the datasource of the dao
	 * 
	 * @param ds
	 */
	public void setDatasource(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
	}

	protected JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
}
