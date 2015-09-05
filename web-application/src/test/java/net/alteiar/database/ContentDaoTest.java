package net.alteiar.database;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import junit.framework.Assert;
import net.alteiar.dao.DaoFactory;
import net.alteiar.dao.campaign.content.TopicDao;
import net.alteiar.model.Access;
import net.alteiar.model.content.Article;
import net.alteiar.model.content.Topic;

public class ContentDaoTest {

	@Before
	public void setup() {

		DatabaseUtil.start();
		DatabaseUtil.reset();
	}

	@After
	public void after() {

		DatabaseUtil.shutdown();
	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void testTopic() {

		Access access = new Access();
		access.setPublic(true);
		DaoFactory.getInstance().getAccessDao().insert(access);

		Topic topic = new Topic();
		topic.setName("dir-name");
		topic.setParentId(null);
		topic.setAccessId(access.getId());

		TopicDao contentDao = DaoFactory.getInstance().getTopicDao();
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
		DaoFactory.getInstance().getAccessDao().insert(access);

		TopicDao contentDao = DaoFactory.getInstance().getTopicDao();
		contentDao.insert(createTopic("dir1", null, access.getId()));
		contentDao.insert(createTopic("dir2", null, access.getId()));
		contentDao.insert(createTopic("dir3", 1L, access.getId()));

		List<Topic> found = DaoFactory.getInstance().getTopicDao().findAll(Arrays.asList(1L, 2L, 3L));

		Assert.assertEquals(3, found.size());

		Assert.assertEquals("dir1", found.get(0).getName());
		Assert.assertEquals("dir2", found.get(1).getName());
		Assert.assertEquals("dir3", found.get(2).getName());

		found = DaoFactory.getInstance().getTopicDao().findAll(Arrays.asList(3L));

		Assert.assertEquals(1, found.size());
		Assert.assertEquals(1L, found.get(0).getParentId().longValue());
	}

	@Test
	public void testFindWithParentCategory() {

		Access access = new Access();
		access.setPublic(true);
		DaoFactory.getInstance().getAccessDao().insert(access);

		TopicDao topicDao = DaoFactory.getInstance().getTopicDao();
		topicDao.insert(createTopic("dir1", null, access.getId()));
		topicDao.insert(createTopic("dir3", 1L, access.getId()));

		Topic found = DaoFactory.getInstance().getTopicDao().find(2L);

		Assert.assertEquals(Long.valueOf(1), found.getParentId());
		Assert.assertEquals("dir3", found.getName());
	}

	@Test
	public void testTopicWithContent() {

		Access access = new Access();
		access.setPublic(true);
		DaoFactory.getInstance().getAccessDao().insert(access);

		Topic topic = createTopic("dir3", null, access.getId());
		topic.setContentId(2L);
		topic.setContentType(Article.class.getCanonicalName());

		TopicDao topicDao = DaoFactory.getInstance().getTopicDao();
		topicDao.insert(topic);

		Topic found = DaoFactory.getInstance().getTopicDao().find(topic.getId());

		Assert.assertEquals(null, found.getParentId());
		Assert.assertEquals("dir3", found.getName());
		Assert.assertEquals(Article.class.getCanonicalName(), found.getContentType());
		Assert.assertEquals(Long.valueOf(2L), found.getContentId());
	}
}
