package info.jawne.kalendarz.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import info.jawne.kalendarz.controllers.commands.LogonCommand;
import info.jawne.kalendarz.dao.EventDao;
import info.jawne.kalendarz.dao.UserDao;
import info.jawne.kalendarz.models.User;

@Controller
public class EventSearchController {
	@Autowired
	private UserDao user_dao;

	@Autowired
	private EventDao event_dao;

	@RequestMapping(value = "/event-search", method = RequestMethod.GET)
	public String details(@RequestParam("query") String query, Model model, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, RedirectAttributes redirectAttributes) {
		LogonCommand logon = (LogonCommand) session.getAttribute("logInSession");
		User user = user_dao.getByUsernameOrNull(logon.getUsername());
		model.addAttribute("query", query);
		model.addAttribute("event_list", event_dao.find(user, query));
		return "eventSearchList";
	}

}