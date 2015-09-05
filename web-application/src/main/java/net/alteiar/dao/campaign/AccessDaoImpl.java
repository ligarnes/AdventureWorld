package net.alteiar.dao.campaign;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import net.alteiar.dao.BaseDao;
import net.alteiar.model.Access;

public class AccessDaoImpl extends BaseDao implements AccessDao {

	private static final String TABLE_NAME = "access";

	private static final String INSERT = "INSERT INTO " + TABLE_NAME + " (is_public) VALUES (?)";

	// private static final String INSERT_MODIFICATION = "INSERT INTO
	// access_modification (access_id, player_id) VALUES (?, ?)";
	// private static final String INSERT_READ_ONLY = "INSERT INTO
	// access_readonly (access_id, player_id) VALUES (?, ?)";

	private static final String UPDATE = "UPDATE " + TABLE_NAME + " SET is_public=? WHERE id=?";

	private static final String FIND_ALL = "SELECT id, is_public FROM " + TABLE_NAME;
	private static final String FIND_BY_ID = FIND_ALL + " WHERE id=?";
	private static final String DELETE = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";

	@Override
	public void insert(final Access access) {

		KeyHolder holder = new GeneratedKeyHolder();

		getJdbcTemplate().update((PreparedStatementCreator) connection -> {
			PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setBoolean(1, access.isPublic());
			return ps;
		} , holder);

		access.setId(holder.getKey().longValue());
	}

	@Override
	public void update(Access access) {

		getJdbcTemplate().update(UPDATE, access.isPublic(), access.getId());
	}

	@Override
	public Access find(long id) {

		return getJdbcTemplate().queryForObject(FIND_BY_ID, new Object[] { id }, new AccessMapper());
	}

	@Override
	public List<Access> findAll() {

		return getJdbcTemplate().query(FIND_ALL, new AccessMapper());
	}

	@Override
	public void delete(long id) {

		getJdbcTemplate().update(DELETE, id);
	}

	private static class AccessMapper implements RowMapper<Access> {

		@Override
		public Access mapRow(ResultSet rs, int rowNum) throws SQLException {

			Access access = new Access();

			access.setId(rs.getLong(1));
			access.setPublic(rs.getBoolean(2));

			return access;
		}
	}

}
