package net.alteiar.campaign.dao.mysql.article.content;

import net.alteiar.campaign.article.content.ContentText;

public interface ContentTextDao extends ContentDao<ContentText> {

	ContentText findById(long contentId);
}
