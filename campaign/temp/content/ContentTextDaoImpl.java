package net.alteiar.campaign.dao.mysql.article.content;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.alteiar.campaign.article.content.ContentText;

public class ContentTextDaoImpl extends ContentDaoImpl<ContentText> implements ContentTextDao {

	private static final String INSERT_CONTENT = "insert into CONTENT_TEXT (CONTENT_ID, TEXT) values (?, ?)";
	private static final String UPDATE_CONTENT = "update CONTENT_TEXT set TEXT = ? where CONTENT_ID = ?";
	private static final String DELETE_CONTENT = "delete from CONTENT_TEXT where CONTENT_ID = ?";

	private static final String FIND_ALL = "select ID, IS_PUBLIC, TEXT from CONTENT, CONTENT_TEXT where ID = CONTENT_ID AND";
	private static final String FIND_BY_ID = FIND_ALL + " ID=?";

	@Override
	protected void insertSpecific(ContentText content) {

		getJdbcTemplate().update(INSERT_CONTENT, getContentTypeName());
	}

	@Override
	protected void updateSpecific(ContentText content) {

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
	public ContentText findById(long contentId) {

		return getJdbcTemplate().queryForObject(FIND_BY_ID, new Object[] { contentId }, new ContentMapper());
	}

	protected static class ContentMapper implements RowMapper<ContentText> {

		@Override
		public ContentText mapRow(ResultSet rs, int rowNum) throws SQLException {

			ContentText content = new ContentText();

			content.setId(rs.getLong(1));
			content.setPublic(rs.getBoolean(2));
			content.setText(rs.getString(3));

			return content;
		}
	}
}
