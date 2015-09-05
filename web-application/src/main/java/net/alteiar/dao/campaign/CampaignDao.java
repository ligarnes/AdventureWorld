package net.alteiar.dao.campaign;

import java.util.List;

import net.alteiar.dao.Dao;
import net.alteiar.model.Campaign;

public interface CampaignDao extends Dao {

	void insert(Campaign campaign);

	void update(Campaign campaign);

	List<Campaign> findAll();

	Campaign find(long id);

	void delete(long id);
}
