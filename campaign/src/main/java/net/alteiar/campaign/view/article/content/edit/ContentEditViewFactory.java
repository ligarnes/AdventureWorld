package net.alteiar.campaign.view.article.content.edit;

import net.alteiar.campaign.article.content.ContentElementText;
import net.alteiar.campaign.article.content.ContentSection;
import net.alteiar.campaign.article.content.ContentText;
import net.alteiar.campaign.bo.article.content.ContentBO;
import net.alteiar.campaign.view.controller.Controller;

public class ContentEditViewFactory {

	public static Controller createContentView(Controller parent, ContentBO content) {

		ContentEditView<?> view = null;

		if (content.getContentData() instanceof ContentSection) {

			view = new ContentSectionEditView();
		} else if (content.getContentData() instanceof ContentText) {

			view = new ContentTextEditView();
		} else if (content.getContentData() instanceof ContentElementText) {

		} else {

			throw new UnsupportedOperationException(
					String.format("The content of class [%s] is not supported", content.getClass()));
		}

		view.load(content);

		System.out.println("dispatcher " + parent.getDispatcher());
		view.setDispatcher(parent.getDispatcher());
		view.setRequest(parent.getRequest());

		return view;
	}
}
