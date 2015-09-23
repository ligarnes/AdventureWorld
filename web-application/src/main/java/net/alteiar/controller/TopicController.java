package net.alteiar.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.alteiar.context.AppContext;
import net.alteiar.model.Access;
import net.alteiar.model.content.Topic;

@Controller
@RequestMapping(value = "/topic")
public class TopicController extends AbstractController {

	@RequestMapping(value = "/list")
	public String listView(Model model) {

		List<Topic> rootTopic = new ArrayList<Topic>();

		List<Topic> found = AppContext.getInstance().getDaoFactory().getTopicDao().findAll();

		for (Topic topic : found) {

			// add root
			if (topic.getParentId() == null) {

				rootTopic.add(topic);
			}

			List<Topic> children = AppContext.getInstance().getDaoFactory().getTopicDao().findChildren(topic);
			model.addAttribute(String.valueOf(topic.getId()), children);
		}

		model.addAttribute("root_topics", rootTopic);

		return "internal/topic/topic_list";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String addTopic(@RequestParam("name") String name, @RequestParam("parent") String parent, Model model) {

		Long parentId = null;

		if (!"null".equalsIgnoreCase(parent)) {

			parentId = Long.valueOf(parent);
		}

		Access access = new Access();
		access.setPublic(false);
		AppContext.getInstance().getDaoFactory().getAccessDao().insert(access);

		Topic topic = new Topic();
		topic.setName(name);
		topic.setAccessId(access.getId());
		topic.setParentId(parentId);
		AppContext.getInstance().getDaoFactory().getTopicDao().insert(topic);

		return "redirect:/topic/list/";
	}

	@Override
	@ExceptionHandler(Exception.class)
	public String handleException(Exception ex) {
		return handleAllException(ex);
	}
}
