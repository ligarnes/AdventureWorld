package net.alteiar.dao;

import javax.sql.DataSource;

public abstract class DaoBuilder<E extends Dao> {

	public E build(DataSource adapter) {

		E created = create();
		init(created, adapter);

		return created;
	}

	/**
	 * Create the dao
	 * 
	 * @return
	 */
	protected abstract E create();

	/**
	 * initialize the dao
	 * 
	 * @param dao
	 * @param adapter
	 */
	protected void init(E dao, DataSource ds) {

		dao.setDatasource(ds);
	}
}
