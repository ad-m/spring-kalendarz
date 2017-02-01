package info.jawne.kalendarz.controllers;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import info.jawne.kalendarz.controllers.commands.EventLookupCommand;
import info.jawne.kalendarz.controllers.editors.CategoryEditor;
import info.jawne.kalendarz.controllers.editors.DurationEditor;
import info.jawne.kalendarz.controllers.editors.DurationTimeEditor;
import info.jawne.kalendarz.controllers.utils.EventLookup;
import info.jawne.kalendarz.dao.CategoryDao;
import info.jawne.kalendarz.dao.DurationTimeDao;
import info.jawne.kalendarz.dao.EventDao;
import info.jawne.kalendarz.dao.UserDao;
import info.jawne.kalendarz.exceptions.AuthorizationException;
import info.jawne.kalendarz.models.Category;
import info.jawne.kalendarz.models.DurationTime;
import info.jawne.kalendarz.models.Message;
import info.jawne.kalendarz.models.User;

@Controller
@RequestMapping("/lookup")
public class EventLookupController {
	private final Log log = LogFactory.getLog(getClass());

	@Autowired
	private CategoryDao category_dao;

	@Autowired
	private EventDao event_dao;

	@Autowired
	private UserDao user_dao;

	private DurationTimeDao durationtime_dao = new DurationTimeDao();

	@RequestMapping(method = RequestMethod.GET)
	public String form(Model model, HttpSession session) throws AuthorizationException {
		User user = user_dao.getByUsernameOrNull((String) session.getAttribute("username"));
		if (user == null) {
			throw new AuthorizationException();
		}
		EventLookupCommand command = new EventLookupCommand();
		command.setDate(new Date());
		model.addAttribute("eventLookupCommand", command);
		return "eventLookupForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String onSubmit(Model model, HttpServletRequest request, HttpServletResponse response,
			@Valid EventLookupCommand command, BindingResult errors, HttpSession session,
			RedirectAttributes redirectAttributes) throws AuthorizationException {
		User user = user_dao.getByUsernameOrNull((String) session.getAttribute("username"));
		if (user == null) {
			throw new AuthorizationException();
		}

		model.addAttribute("eventLookupCommand", command);
		log.info("wyswietlenie ekranu " + new Exception().getStackTrace()[0]);

		if (errors.hasErrors()) {
			return "eventLookupForm";
		} else {
			session.setAttribute("eventLookupCommand", command);

			Date start = command.getStart();
			Date end = command.getEnd();
			model.addAttribute("event_list", event_dao.getRange(user, start, end));
			model.addAttribute("match_list", new EventLookup(event_dao, user, start, end, command.getDurationEvent()));
			redirectAttributes.addFlashAttribute("message", new Message(Message.Status.SUCCESS, "Witaj świecie!"));

			return "eventLookupSuggestionList";

		}

	}

	@ModelAttribute("days")
	protected Object fill_days() {
		return durationtime_dao.getAll();
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		binder.registerCustomEditor(Category.class, new CategoryEditor(category_dao));
		binder.registerCustomEditor(Duration.class, new DurationEditor());
		binder.registerCustomEditor(DurationTime.class, new DurationTimeEditor(durationtime_dao));
	}

}
