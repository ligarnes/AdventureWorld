package net.alteiar.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class BaseDao {

	private JdbcTemplate jdbcTemplate;

	public void setDatasource(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
	}

	protected JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
}
