package net.alteiar.database;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import junit.framework.Assert;
import net.alteiar.dao.campaign.AccessDao;
import net.alteiar.dao.campaign.AccessDaoImpl;
import net.alteiar.dao.campaign.content.TopicDao;
import net.alteiar.dao.campaign.content.TopicDaoImpl;
import net.alteiar.model.Access;
import net.alteiar.model.content.Article;
import net.alteiar.model.content.Topic;

public class ContentDaoTest {

	@BeforeClass
	public static void setup() {

		DatabaseUtil.start();
	}

	private TopicDao topicDao;
	private AccessDao accessDao;

	@Before
	public void before() {

		DatabaseUtil.reset();

		topicDao = new TopicDaoImpl();
		topicDao.setDatasource(DatabaseUtil.getDatasource());

		accessDao = new AccessDaoImpl();
		accessDao.setDatasource(DatabaseUtil.getDatasource());
	}

	@After
	public void after() {

		DatabaseUtil.reset();
		topicDao = null;
		accessDao = null;
	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void testTopic() {

		Access access = new Access();
		access.setPublic(true);
		accessDao.insert(access);

		Topic topic = new Topic();
		topic.setName("dir-name");
		topic.setParentId(null);
		topic.setAccessId(access.getId());

		TopicDao contentDao = topicDao;
		contentDao.insert(topic);

		Topic found = contentDao.find(topic.getId());

		Assert.assertEquals("dir-name", found.getName());
		Assert.assertNull(found.getParentId());
		Assert.assertEquals(access.getId(), found.getAccessId());

		contentDao.delete(topic.getId());

		contentDao.find(topic.getId());
	}

	private Topic createTopic(String name, Long parentId, long accessId) {

		Topic topic = new Topic();
		topic.setName(name);
		topic.setParentId(parentId);
		topic.setAccessId(accessId);

		return topic;
	}

	@Test
	public void testFindMultipleCategory() {

		Access access = new Access();
		access.setPublic(true);
		accessDao.insert(access);

		TopicDao contentDao = topicDao;
		contentDao.insert(createTopic("dir1", null, access.getId()));
		contentDao.insert(createTopic("dir2", null, access.getId()));
		contentDao.insert(createTopic("dir3", 1L, access.getId()));

		List<Topic> found = topicDao.findAll(Arrays.asList(1L, 2L, 3L));

		Assert.assertEquals(3, found.size());

		Assert.assertEquals("dir1", found.get(0).getName());
		Assert.assertEquals("dir2", found.get(1).getName());
		Assert.assertEquals("dir3", found.get(2).getName());

		found = topicDao.findAll(Arrays.asList(3L));

		Assert.assertEquals(1, found.size());
		Assert.assertEquals(1L, found.get(0).getParentId().longValue());
	}

	@Test
	public void testFindWithParentCategory() {

		Access access = new Access();
		access.setPublic(true);
		accessDao.insert(access);

		topicDao.insert(createTopic("dir1", null, access.getId()));
		topicDao.insert(createTopic("dir3", 1L, access.getId()));

		Topic found = topicDao.find(2L);

		Assert.assertEquals(Long.valueOf(1), found.getParentId());
		Assert.assertEquals("dir3", found.getName());
	}

	@Test
	public void testTopicWithContent() {

		Access access = new Access();
		access.setPublic(true);
		accessDao.insert(access);

		Topic topic = createTopic("dir3", null, access.getId());
		topic.setContentId(2L);
		topic.setContentType(Article.class.getCanonicalName());

		topicDao.insert(topic);

		Topic found = topicDao.find(topic.getId());

		Assert.assertEquals(null, found.getParentId());
		Assert.assertEquals("dir3", found.getName());
		Assert.assertEquals(Article.class.getCanonicalName(), found.getContentType());
		Assert.assertEquals(Long.valueOf(2L), found.getContentId());
	}
}
