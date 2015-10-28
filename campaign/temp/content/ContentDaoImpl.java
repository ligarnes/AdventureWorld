package net.alteiar.campaign.dao.mysql.article.content;

import java.sql.PreparedStatement;
import java.sql.Statement;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import net.alteiar.campaign.article.content.BaseContent;
import net.alteiar.dao.BaseDao;

public abstract class ContentDaoImpl<E extends BaseContent> extends BaseDao {

	private static final String INSERT_CONTENT = "insert into CONTENT (CONTENT_TYPE, IS_PUBLIC) values (?, ?)";
	private static final String UPDATE_CONTENT = "update CONTENT set IS_PUBLIC = ? where ID = ?";
	private static final String DELETE_CONTENT = "delete from CONTENT where ID = ?";

	private static final String FIND_ALL = "select ID, IS_PUBLIC from CONTENT";

	public void delete(E content) {

		delete(content.getId());
	}

	public void delete(long contentId) {

		deleteContent(contentId);
		deleteSpecific(contentId);
	}

	private void deleteContent(long contentId) {

		getJdbcTemplate().update(DELETE_CONTENT, contentId);
	}

	public void update(E content) {

		updateContent(content);
		updateSpecific(content);
	}

	private void updateContent(E content) {

		getJdbcTemplate().update(UPDATE_CONTENT, content.isPublic(), content.getId());
	}

	public void insert(E content) {

		insertContent(content);
		insertSpecific(content);
	}

	private void insertContent(final E content) {

		getJdbcTemplate().update(INSERT_CONTENT, getContentTypeName());

		KeyHolder holder = new GeneratedKeyHolder();

		getJdbcTemplate().update((PreparedStatementCreator) connection -> {
			PreparedStatement ps = connection.prepareStatement(INSERT_CONTENT, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, getContentTypeName());
			ps.setBoolean(2, content.isPublic());

			return ps;
		} , holder);

		content.setId(holder.getKey().longValue());
	}

	protected abstract void insertSpecific(E content);

	protected abstract void updateSpecific(E content);

	protected abstract void deleteSpecific(long contentId);

	protected abstract String getContentTypeName();

}
