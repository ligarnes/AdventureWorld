package net.alteiar.dao;

import org.junit.Test;

import junit.framework.Assert;

public class BaseDaoTest {

	@Test
	public void testBaseDao() {

		BaseDao dao = new BaseDao();

		Assert.assertNull(dao.getJdbcTemplate());
	}
}
