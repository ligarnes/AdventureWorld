package net.alteiar.dao.campaign;

import java.util.List;

import net.alteiar.dao.Dao;
import net.alteiar.model.Access;

public interface AccessDao extends Dao {

	void insert(Access access);

	void update(Access access);

	List<Access> findAll();

	Access find(long id);

	void delete(long id);
}
