package net.alteiar.campaign.dao.mysql.article.content;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.alteiar.campaign.article.content.ContentSection;

public class ContentSectionDaoImpl extends ContentDaoImpl<ContentSection> implements ContentSectionDao {

	private static final String INSERT_CONTENT = "insert into CONTENT_SECTION (CONTENT_ID, TITLE) values (?, ?)";
	private static final String UPDATE_CONTENT = "update CONTENT_SECTION set TITLE = ? where CONTENT_ID = ?";
	private static final String DELETE_CONTENT = "delete from CONTENT_SECTION where CONTENT_ID = ?";

	private static final String FIND_ALL = "select ID, IS_PUBLIC, TITLE from CONTENT, CONTENT_SECTION where ID = CONTENT_ID AND";
	private static final String FIND_BY_ID = FIND_ALL + " ID=?";

	@Override
	protected void insertSpecific(ContentSection content) {
		getJdbcTemplate().update(INSERT_CONTENT, getContentTypeName());
	}

	@Override
	protected void updateSpecific(ContentSection content) {

		getJdbcTemplate().update(UPDATE_CONTENT, content.isPublic(), content.getId());
	}

	@Override
	protected void deleteSpecific(long contentId) {

		getJdbcTemplate().update(DELETE_CONTENT, contentId);
	}

	@Override
	protected String getContentTypeName() {
		return "text";
	}

	@Override
	public ContentSection findById(long contentId) {

		return getJdbcTemplate().queryForObject(FIND_BY_ID, new Object[] { contentId }, new ContentMapper());
	}

	protected static class ContentMapper implements RowMapper<ContentSection> {

		@Override
		public ContentSection mapRow(ResultSet rs, int rowNum) throws SQLException {

			ContentSection content = new ContentSection();

			content.setId(rs.getLong(1));
			content.setPublic(rs.getBoolean(2));
			content.setTitle(rs.getString(3));

			return content;
		}
	}

}
