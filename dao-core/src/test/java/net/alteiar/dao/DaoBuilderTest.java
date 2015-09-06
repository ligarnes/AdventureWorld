package net.alteiar.dao;

import javax.sql.DataSource;

import org.junit.Test;
import org.mockito.Mockito;

import junit.framework.Assert;

public class DaoBuilderTest {

	@Test
	public void testBaseDao() {

		BaseDao dao = new BaseDao();

		Assert.assertNull(dao.getJdbcTemplate());
	}

	@Test
	public void testDaoBuilder() {

		DaoBuilder<BaseDao> builder = new DaoBuilder<BaseDao>() {

			@Override
			protected BaseDao create() {
				return new BaseDao();
			}
		};

		DataSource ds = Mockito.mock(DataSource.class);

		BaseDao dao = builder.build(ds);

		Assert.assertEquals(ds, dao.getJdbcTemplate().getDataSource());
	}
}
