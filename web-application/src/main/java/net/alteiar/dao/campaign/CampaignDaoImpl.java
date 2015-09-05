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
import net.alteiar.model.Campaign;

public class CampaignDaoImpl extends BaseDao implements CampaignDao {

	private static final String TABLE_NAME = "campaign";

	private static final String INSERT = "INSERT INTO " + TABLE_NAME + " (name) VALUES (?)";

	private static final String UPDATE = "UPDATE " + TABLE_NAME + " SET name=? WHERE id=?";

	private static final String FIND_ALL = "SELECT id, name FROM " + TABLE_NAME;
	private static final String FIND_BY_ID = FIND_ALL + " WHERE id=?";
	private static final String DELETE = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";

	private static final String TABLE_CAMPAIGN_CONTENT = "campaign_content";
	private static final String DELETE_CONTENT = "DELETE FROM " + TABLE_CAMPAIGN_CONTENT + " WHERE id_campaign = ?";
	private static final String ADD_CONTENT = "INSERT INTO " + TABLE_CAMPAIGN_CONTENT + " (?, ?)";

	private static final String FIND_CONTENT = "SELECT id_content WHERE id_campaign=?";

	@Override
	public void insert(final Campaign campaign) {

		KeyHolder holder = new GeneratedKeyHolder();

		getJdbcTemplate().update((PreparedStatementCreator) connection -> {
			PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, campaign.getName());
			return ps;
		} , holder);

		campaign.setId(holder.getKey().longValue());
	}

	@Override
	public void update(Campaign campaign) {

		getJdbcTemplate().update(UPDATE, campaign.getName(), campaign.getId());

		// Delete content
		getJdbcTemplate().update(DELETE_CONTENT, campaign.getId());

		// Add all content
		for (Long contentId : campaign.getContent()) {

			getJdbcTemplate().update(ADD_CONTENT, campaign.getId(), contentId);
		}
	}

	@Override
	public Campaign find(long id) {

		return getJdbcTemplate().queryForObject(FIND_BY_ID, new Object[] { id }, new CampaignMapper());
	}

	@Override
	public List<Campaign> findAll() {

		return getJdbcTemplate().query(FIND_ALL, new CampaignMapper());
	}

	@Override
	public void delete(long id) {

		getJdbcTemplate().update(DELETE, id);
	}

	private class CampaignMapper implements RowMapper<Campaign> {

		@Override
		public Campaign mapRow(ResultSet rs, int rowNum) throws SQLException {

			Campaign campaign = new Campaign();

			campaign.setId(rs.getLong(1));

			List<Long> contentIds = getJdbcTemplate().queryForList(FIND_CONTENT, Long.class, campaign.getId());

			campaign.getContent().addAll(contentIds);

			return campaign;
		}
	}

}
