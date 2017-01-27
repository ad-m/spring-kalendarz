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

import dao.EventDao;
import dao.UserDao;
import info.jawne.kalendarz.controllers.commands.LogonCommand;
import model.Month;
import model.User;
import model.Week;
import model.Year;

@Controller
public class EventRangeListController {
	@Autowired
	private UserDao user_dao;

	@Autowired
	private EventDao event_dao;

	@RequestMapping(value = "/events/", method = RequestMethod.GET)
	public String list(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session,
			RedirectAttributes redirectAttributes) {
		LogonCommand logon = (LogonCommand) session.getAttribute("logInSession");
		User user = user_dao.getByUsernameOrNull(logon.getUsername());
		model.addAttribute("year_list", event_dao.getYearList(user));
		return "eventYearList";
	}

	@RequestMapping(value = "/events/{year}", method = RequestMethod.GET)
	public String list(@PathVariable int year, Model model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, RedirectAttributes redirectAttributes) {
		LogonCommand logon = (LogonCommand) session.getAttribute("logInSession");
		User user = user_dao.getByUsernameOrNull(logon.getUsername());
		Year year_obj = new Year(year);
		model.addAttribute("year", year_obj);
		model.addAttribute("months_list", event_dao.getMonthList(user, year_obj));
		return "eventMonthList";
	}

	@RequestMapping(value = "/events/{year}/{month}", method = RequestMethod.GET)
	public String list(@PathVariable int year, @PathVariable int month, Model model, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, RedirectAttributes redirectAttributes) {
		LogonCommand logon = (LogonCommand) session.getAttribute("logInSession");
		User user = user_dao.getByUsernameOrNull(logon.getUsername());
		Year year_obj = new Year(year);
		model.addAttribute("year", year_obj);
		Month month_obj = new Month(month, year);
		model.addAttribute("month", month_obj);
		model.addAttribute("weeks_list", event_dao.getWeekList(user, month_obj));
		return "eventWeekList";
	}

	@RequestMapping(value = "/events/{year}/{month}/{week}", method = RequestMethod.GET)
	public String list(@PathVariable int year, @PathVariable int month, @PathVariable int week, Model model,
			HttpServletRequest request, HttpServletResponse response, HttpSession session,
			RedirectAttributes redirectAttributes) {
		LogonCommand logon = (LogonCommand) session.getAttribute("logInSession");
		User user = user_dao.getByUsernameOrNull(logon.getUsername());
		Month month_obj = new Month(month, year);
		Week week_obj = new Week(month_obj, week);
		model.addAttribute("month", month_obj);
		model.addAttribute("week", week_obj);
		model.addAttribute("event_list", event_dao.getWeek(user, week_obj));
		return "eventWeekDetail";
	}
}