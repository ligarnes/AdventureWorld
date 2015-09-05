package net.alteiar.dao.campaign;

import java.util.List;

import net.alteiar.dao.Dao;
import net.alteiar.model.Player;

public interface PlayerDao extends Dao {

	void insert(Player user);

	void update(Player user);

	List<Player> findAll();

	Player find(long id);

	void delete(long id);
}
