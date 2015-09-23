package net.alteiar.dao.campaign.content;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import net.alteiar.dao.BaseDao;
import net.alteiar.model.content.Topic;

public class TopicDaoImpl extends BaseDao implements TopicDao {

	private static final String TABLE_NAME = "topic";

	private static final String INSERT = "INSERT INTO " + TABLE_NAME
			+ "(parent_id, name, id_access, id_content, content_type) VALUES (?, ?, ?, ?, ?)";

	private static final String UPDATE = "UPDATE " + TABLE_NAME + " SET parent_id=?,  name=?, id_access=? WHERE id=?";

	private static final String FIND = "SELECT id, name, parent_id, id_access, id_content, content_type FROM "
			+ TABLE_NAME;

	private static final String FIND_BY_ID = FIND + " WHERE id = ?";

	private static final String FIND_CHILDREN_OF = FIND + " WHERE parent_id = ?";

	private static final String DELETE = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";

	@Override
	public void insert(final Topic topic) {

		KeyHolder holder = new GeneratedKeyHolder();

		getJdbcTemplate().update((PreparedStatementCreator) connection -> {
			PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			if (topic.getParentId() != null) {

				ps.setLong(1, topic.getParentId());
			} else {

				ps.setNull(1, Types.BIGINT);
			}
			ps.setString(2, topic.getName());
			ps.setLong(3, topic.getAccessId());

			if (topic.getContentId() != null) {

				ps.setLong(4, topic.getContentId());
				ps.setString(5, topic.getContentType());
			} else {

				ps.setNull(4, Types.BIGINT);
				ps.setNull(5, Types.VARCHAR);
			}

			return ps;
		} , holder);

		topic.setId(holder.getKey().longValue());
	}

	@Override
	public void update(Topic topic) {

		getJdbcTemplate().update(UPDATE, topic.getParentId(), topic.getName(), topic.getAccessId(), topic.getId());
	}

	@Override
	public List<Topic> findAll(List<Long> ids) {

		StringBuilder query = new StringBuilder(FIND);
		Object[] params = new Object[ids.size()];

		query.append(" WHERE ");

		StringBuilder where = new StringBuilder();
		int idx = 0;
		for (long id : ids) {

			params[idx] = id;
			where.append(" OR id=?");
			idx++;
		}

		query.append(where.substring(4, where.length()));

		return getJdbcTemplate().query(query.toString(), params, new TopicMapper());
	}

	@Override
	public Topic find(long id) {

		return getJdbcTemplate().queryForObject(FIND_BY_ID, new Object[] { id }, new TopicMapper());
	}

	@Override
	public void delete(long id) {

		getJdbcTemplate().update(DELETE, id);
	}

	@Override
	public List<Topic> findChildren(Topic topic) {

		return getJdbcTemplate().query(FIND_CHILDREN_OF, new TopicMapper(), topic.getId());
	}

	private class TopicMapper implements RowMapper<Topic> {

		@Override
		public Topic mapRow(ResultSet rs, int rowNum) throws SQLException {

			long id = rs.getLong(1);
			String name = rs.getString(2);

			Long parentId = rs.getLong(3);

			if (rs.wasNull()) {

				parentId = null;
			}

			Long accessId = rs.getLong(4);

			Long contentId = rs.getLong(5);

			String contentType = rs.getString(6);

			Topic topic = new Topic();

			topic.setId(id);
			topic.setName(name);

			topic.setParentId(parentId);
			topic.setAccessId(accessId);

			topic.setContentId(contentId);
			topic.setContentType(contentType);

			return topic;
		}
	}

	@Override
	public List<Topic> findAll() {

		return getJdbcTemplate().query(FIND, new TopicMapper());
	}
}
