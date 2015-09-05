package net.alteiar.dao.campaign.content;

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
import net.alteiar.model.content.Article;

public class ArticleDaoImpl extends BaseDao implements ArticleDao {

	private static final String INSERT = "INSERT INTO article (title, content) VALUES (?, ?)";
	private static final String UPDATE = "UPDATE article SET title=?, content=?  WHERE id=?";
	private static final String FIND_ALL = "SELECT id, title, content FROM article";
	private static final String FIND_BY_ID = FIND_ALL + " WHERE id=?";
	private static final String DELETE = "DELETE FROM article WHERE id = ?";

	@Override
	public void insert(final Article article) {

		KeyHolder holder = new GeneratedKeyHolder();

		getJdbcTemplate().update((PreparedStatementCreator) connection -> {
			PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, article.getTitle());
			ps.setString(2, article.getContent());

			return ps;
		} , holder);

		article.setId(holder.getKey().longValue());
	}

	@Override
	public void update(Article article) {

		getJdbcTemplate().update(UPDATE, article.getTitle(), article.getContent(), article.getId());
	}

	@Override
	public Article find(long id) {

		return getJdbcTemplate().queryForObject(FIND_BY_ID, new Object[] { id }, new ArticleMapper());
	}

	@Override
	public List<Article> findAll() {

		return getJdbcTemplate().query(FIND_ALL, new ArticleMapper());
	}

	@Override
	public void delete(long id) {

		getJdbcTemplate().update(DELETE, id);
	}

	private static class ArticleMapper implements RowMapper<Article> {

		@Override
		public Article mapRow(ResultSet rs, int rowNum) throws SQLException {

			Article article = new Article();

			article.setId(rs.getLong(1));
			article.setTitle(rs.getString(2));
			article.setContent(rs.getString(3));

			return article;
		}
	}

}
