package net.alteiar.campaign.dao.mysql.article.content;

import net.alteiar.campaign.article.content.ContentSection;

public interface ContentSectionDao extends ContentDao<ContentSection> {

	ContentSection findById(long contentId);
}
