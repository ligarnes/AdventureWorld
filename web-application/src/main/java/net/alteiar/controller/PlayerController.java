package net.alteiar.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.alteiar.dao.DaoFactory;
import net.alteiar.dao.campaign.PlayerDao;
import net.alteiar.model.Player;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/player")
public class PlayerController extends AbstractController {

	private static final Logger logger = LoggerFactory.getLogger(PlayerController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {

		PlayerDao dao = DaoFactory.getInstance().getPlayerDao();

		logger.debug("Find all players");
		List<Player> players = dao.findAll();

		model.addAttribute("players", players);

		return "player_list";
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String home(@RequestParam("name") String name,
			@RequestParam(value = "isDM", required = false, defaultValue = "false") boolean isDm, Model model) {

		Player newPlayer = new Player();
		newPlayer.setName(name);
		newPlayer.setDm(isDm);

		DaoFactory.getInstance().getPlayerDao().insert(newPlayer);

		return "redirect:/player/";
	}

	@Override
	@ExceptionHandler(Exception.class)
	public String handleException(Exception ex) {
		return handleAllException(ex);
	}
}
