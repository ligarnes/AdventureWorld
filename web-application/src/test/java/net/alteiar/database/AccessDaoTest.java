package net.alteiar.database;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import junit.framework.Assert;
import net.alteiar.dao.campaign.AccessDao;
import net.alteiar.dao.campaign.AccessDaoImpl;
import net.alteiar.model.Access;

public class AccessDaoTest {

	@BeforeClass
	public static void setup() {

		DatabaseUtil.start();
	}

	private AccessDao dao;

	@Before
	public void before() {

		DatabaseUtil.reset();

		dao = new AccessDaoImpl();
		dao.setDatasource(DatabaseUtil.getDatasource());
	}

	@After
	public void after() {

		DatabaseUtil.reset();
		dao = null;
	}

	@Test
	public void testInsert() {

		Access access = new Access();
		access.setPublic(true);
		dao.insert(access);

		Assert.assertNotNull(access.getId());

		Access found = dao.find(access.getId());

		Assert.assertEquals(access.getId(), found.getId());
		Assert.assertEquals(access.isPublic(), found.isPublic());

		Access accessSecond = new Access();
		accessSecond.setPublic(true);
		dao.insert(accessSecond);

		found = dao.find(accessSecond.getId());
		Assert.assertEquals(accessSecond.getId(), found.getId());
		Assert.assertEquals(accessSecond.isPublic(), found.isPublic());
	}

	@Test
	public void testFindAll() {

		List<Access> allAccessFound = dao.findAll();

		Assert.assertEquals(0, allAccessFound.size());

		Access access = new Access();
		access.setPublic(true);
		dao.insert(access);

		allAccessFound = dao.findAll();
		Assert.assertEquals(1, allAccessFound.size());

		Access access2 = new Access();
		access2.setPublic(true);
		dao.insert(access2);

		allAccessFound = dao.findAll();
		Assert.assertEquals(2, allAccessFound.size());
	}

	@Test
	public void testUpdate() {

		Access access = new Access();
		access.setPublic(true);
		dao.insert(access);

		Assert.assertNotNull(access.getId());

		Access found = dao.find(access.getId());

		Assert.assertEquals(access.getId(), found.getId());
		Assert.assertEquals(access.isPublic(), found.isPublic());

		access.setPublic(false);

		dao.update(access);

		found = dao.find(access.getId());
		Assert.assertEquals(access.getId(), found.getId());
		Assert.assertEquals(access.isPublic(), found.isPublic());
	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void testDelete() {

		Access access = new Access();
		access.setPublic(true);
		dao.insert(access);

		Assert.assertNotNull(access.getId());

		Access found = dao.find(access.getId());

		Assert.assertEquals(access.getId(), found.getId());
		Assert.assertEquals(access.isPublic(), found.isPublic());

		dao.delete(access.getId());

		dao.find(access.getId());
	}
}
