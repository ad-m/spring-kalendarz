package info.jawne.kalendarz.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import info.jawne.kalendarz.dao.EventDao;
import info.jawne.kalendarz.dao.UserDao;
import info.jawne.kalendarz.exceptions.AuthorizationException;
import info.jawne.kalendarz.models.User;
import info.jawne.kalendarz.models.Week;

@Controller
public class EventRangeListController {
	@Autowired
	private EventDao event_dao;

	@Autowired
	private UserDao user_dao;
	private final Log log = LogFactory.getLog(getClass());

	@RequestMapping(value = "/events/", method = RequestMethod.GET)
	public String list_global(Model model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, RedirectAttributes redirectAttributes) throws AuthorizationException {
		User user = user_dao.getByUsernameOrNull((String) session.getAttribute("username"));
		if (user == null) {
			throw new AuthorizationException();
		}
		model.addAttribute("event_list", event_dao.all(user));
		return "eventList";
	}

	@RequestMapping(value = "/events/{year}/{week}", method = RequestMethod.GET)
	public String list_week(@PathVariable int year, @PathVariable int week, Model model, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, RedirectAttributes redirectAttributes)
			throws AuthorizationException {
		User user = user_dao.getByUsernameOrNull((String) session.getAttribute("username"));
		if (user == null) {
			throw new AuthorizationException();
		}
		Week week_obj = new Week(year, week);

		model.addAttribute("week", week_obj);
		model.addAttribute("event_list", event_dao.getWeek(user, week_obj));
		model.addAttribute("event_list2", event_dao.getWeek(user, week_obj));
		return "eventWeekDetail";
	}
}