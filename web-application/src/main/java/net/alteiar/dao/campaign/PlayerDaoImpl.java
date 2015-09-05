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
import net.alteiar.model.Player;

public class PlayerDaoImpl extends BaseDao implements PlayerDao {

	private static final String INSERT = "INSERT INTO player (name, is_mj) VALUES (?, ?)";
	private static final String UPDATE = "UPDATE player SET name=?, is_mj=?  WHERE player_id=?";
	private static final String FIND_ALL = "SELECT player_id, name, is_mj FROM player";
	private static final String FIND_BY_ID = FIND_ALL + " WHERE player_id=?";
	private static final String DELETE = "DELETE FROM player WHERE player_id = ?";

	@Override
	public void insert(final Player user) {

		KeyHolder holder = new GeneratedKeyHolder();

		getJdbcTemplate().update((PreparedStatementCreator) connection -> {
			PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getName());
			ps.setBoolean(2, user.isDm());
			return ps;
		} , holder);

		user.setId(holder.getKey().longValue());
	}

	@Override
	public void update(Player user) {

		getJdbcTemplate().update(UPDATE, user.getName(), user.getId());
	}

	@Override
	public Player find(long id) {

		return getJdbcTemplate().queryForObject(FIND_BY_ID, new Object[] { id }, new PlayerMapper());
	}

	@Override
	public List<Player> findAll() {

		return getJdbcTemplate().query(FIND_ALL, new PlayerMapper());
	}

	@Override
	public void delete(long id) {

		getJdbcTemplate().update(DELETE, id);
	}

	private static class PlayerMapper implements RowMapper<Player> {

		@Override
		public Player mapRow(ResultSet rs, int rowNum) throws SQLException {

			Player player = new Player();

			player.setId(rs.getLong(1));
			player.setName(rs.getString(2));
			player.setDm(rs.getBoolean(3));

			return player;
		}
	}

}
