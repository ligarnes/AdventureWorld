package net.alteiar.database;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import junit.framework.Assert;
import net.alteiar.dao.DaoFactory;
import net.alteiar.model.Access;

public class AccessDaoTest {

	@BeforeClass
	public static void setup() {

		DatabaseUtil.start();
	}

	@Before
	public void before() {

		DatabaseUtil.reset();
	}

	@AfterClass
	public static void after() {

		DatabaseUtil.shutdown();
	}

	@Test
	public void testInsert() {

		Access access = new Access();
		access.setPublic(true);
		DaoFactory.getInstance().getAccessDao().insert(access);

		Assert.assertNotNull(access.getId());

		Access found = DaoFactory.getInstance().getAccessDao().find(access.getId());

		Assert.assertEquals(access.getId(), found.getId());
		Assert.assertEquals(access.isPublic(), found.isPublic());

		Access accessSecond = new Access();
		accessSecond.setPublic(true);
		DaoFactory.getInstance().getAccessDao().insert(accessSecond);

		found = DaoFactory.getInstance().getAccessDao().find(accessSecond.getId());
		Assert.assertEquals(accessSecond.getId(), found.getId());
		Assert.assertEquals(accessSecond.isPublic(), found.isPublic());
	}

	@Test
	public void testFindAll() {

		List<Access> allAccessFound = DaoFactory.getInstance().getAccessDao().findAll();

		Assert.assertEquals(0, allAccessFound.size());

		Access access = new Access();
		access.setPublic(true);
		DaoFactory.getInstance().getAccessDao().insert(access);

		allAccessFound = DaoFactory.getInstance().getAccessDao().findAll();
		Assert.assertEquals(1, allAccessFound.size());

		Access access2 = new Access();
		access2.setPublic(true);
		DaoFactory.getInstance().getAccessDao().insert(access2);

		allAccessFound = DaoFactory.getInstance().getAccessDao().findAll();
		Assert.assertEquals(2, allAccessFound.size());
	}

	@Test
	public void testUpdate() {

		Access access = new Access();
		access.setPublic(true);
		DaoFactory.getInstance().getAccessDao().insert(access);

		Assert.assertNotNull(access.getId());

		Access found = DaoFactory.getInstance().getAccessDao().find(access.getId());

		Assert.assertEquals(access.getId(), found.getId());
		Assert.assertEquals(access.isPublic(), found.isPublic());

		access.setPublic(false);

		DaoFactory.getInstance().getAccessDao().update(access);

		found = DaoFactory.getInstance().getAccessDao().find(access.getId());
		Assert.assertEquals(access.getId(), found.getId());
		Assert.assertEquals(access.isPublic(), found.isPublic());
	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void testDelete() {

		Access access = new Access();
		access.setPublic(true);
		DaoFactory.getInstance().getAccessDao().insert(access);

		Assert.assertNotNull(access.getId());

		Access found = DaoFactory.getInstance().getAccessDao().find(access.getId());

		Assert.assertEquals(access.getId(), found.getId());
		Assert.assertEquals(access.isPublic(), found.isPublic());

		DaoFactory.getInstance().getAccessDao().delete(access.getId());

		DaoFactory.getInstance().getAccessDao().find(access.getId());
	}
}
