package info.jawne.kalendarz.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import info.jawne.kalendarz.controllers.commands.LogonCommand;
import info.jawne.kalendarz.dao.EventDao;
import info.jawne.kalendarz.dao.UserDao;
import info.jawne.kalendarz.models.User;
import info.jawne.kalendarz.models.Week;

@Controller
public class EventRangeListController {
	@Autowired
	private UserDao user_dao;

	@Autowired
	private EventDao event_dao;

	@RequestMapping(value = "/events/{year}/{week}", method = RequestMethod.GET)
	public String list(@PathVariable int year, @PathVariable int month, @PathVariable int week, Model model,
			HttpServletRequest request, HttpServletResponse response, HttpSession session,
			RedirectAttributes redirectAttributes) {
		LogonCommand logon = (LogonCommand) session.getAttribute("logInSession");
		User user = user_dao.getByUsernameOrNull(logon.getUsername());
		Week week_obj = new Week(year, week);
		model.addAttribute("week", week_obj);
		model.addAttribute("event_list", event_dao.getWeek(user, week_obj));
		return "eventWeekDetail";
	}
}