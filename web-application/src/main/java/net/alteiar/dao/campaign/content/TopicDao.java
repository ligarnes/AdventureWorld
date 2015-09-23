package net.alteiar.dao.campaign.content;

import java.util.List;

import net.alteiar.dao.Dao;
import net.alteiar.model.content.Topic;

public interface TopicDao extends Dao {

	void insert(Topic category);

	void update(Topic category);

	Topic find(long id);

	List<Topic> findAll();

	List<Topic> findAll(List<Long> ids);

	List<Topic> findChildren(Topic topic);

	void delete(long id);
}
