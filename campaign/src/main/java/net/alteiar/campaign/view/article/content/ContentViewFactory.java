package net.alteiar.campaign.view.article.content;

import net.alteiar.campaign.article.content.ContentElementText;
import net.alteiar.campaign.article.content.ContentSection;
import net.alteiar.campaign.article.content.ContentText;
import net.alteiar.campaign.bo.article.content.ContentBO;
import net.alteiar.campaign.view.controller.Controller;

public class ContentViewFactory {

	public static Controller createContentView(Controller parent, ContentBO content) {

		ContentView<?> view = null;

		if (content.getContentData() instanceof ContentSection) {

			view = new ContentSectionView();
		} else if (content.getContentData() instanceof ContentText) {

			view = new ContentTextView();
		} else if (content.getContentData() instanceof ContentElementText) {

		} else {

			throw new UnsupportedOperationException(
					String.format("The content of class [%s] is not supported", content.getClass()));
		}

		view.setDispatcher(parent.getDispatcher());
		view.setRequest(parent.getRequest());
		view.load(content);

		return view;
	}
}
