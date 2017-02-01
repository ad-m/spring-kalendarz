package info.jawne.kalendarz.controllers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import info.jawne.kalendarz.controllers.commands.EventLookupCommand;
import info.jawne.kalendarz.controllers.editors.CategoryEditor;
import info.jawne.kalendarz.controllers.utils.EventLookup;
import info.jawne.kalendarz.dao.CategoryDao;
import info.jawne.kalendarz.dao.EventDao;
import info.jawne.kalendarz.dao.UserDao;
import info.jawne.kalendarz.exceptions.AuthorizationException;
import info.jawne.kalendarz.models.Category;
import info.jawne.kalendarz.models.Event;
import info.jawne.kalendarz.models.Message;
import info.jawne.kalendarz.models.User;

@Controller
@RequestMapping("/events/~create")
public class EventFormController {
	@Autowired
	private CategoryDao category_dao;

	@Autowired
	private EventDao event_dao;

	private final Log log = LogFactory.getLog(getClass());

	@Autowired
	private ReloadableResourceBundleMessageSource messageSource;

	@Autowired
	private UserDao user_dao;

	@Autowired
	private LocalValidatorFactoryBean validator;

	@RequestMapping(method = RequestMethod.POST)
	public String create(@ModelAttribute("event") Event event, BindingResult result, HttpSession session,
			RedirectAttributes redirectAttributes) throws AuthorizationException {
		return updateOrCreate(event, result, session, redirectAttributes);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String createForm(Model model, HttpSession session) throws AuthorizationException {
		User user = user_dao.getByUsernameOrNull((String) session.getAttribute("username"));
		if (user == null) {
			throw new AuthorizationException();
		}
		Event event = new Event();
		event.setEventStart(new Date());
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MINUTE, 15);
		event.setEventEnd(c.getTime());

		return form(model, event, session);
	}

	@RequestMapping(value = "suggestion-{id}", method = RequestMethod.GET)
	public String suggestionForm(Model model, @PathVariable int id, HttpSession session) throws AuthorizationException {
		User user = user_dao.getByUsernameOrNull((String) session.getAttribute("username"));
		if (user == null) {
			throw new AuthorizationException();
		}
		EventLookupCommand command = (EventLookupCommand) session.getAttribute("eventLookupCommand");

		EventLookup lookup = new EventLookup(event_dao, user, command.getStart(), command.getEnd(),
				command.getDurationEvent());
		Event event = new Event();
		Date start = lookup.getList().get(id).getEvent().getEventEnd();
		event.setEventStart(start);
		// end = start + duration
		event.setEventEnd(Date.from(start.toInstant().plus(command.getDurationEvent())));

		return form(model, event, session);
	};

	private String form(Model model, Event attributeValue, HttpSession session) throws AuthorizationException {
		User user = user_dao.getByUsernameOrNull((String) session.getAttribute("username"));
		if (user == null) {
			throw new AuthorizationException();
		}
		model.addAttribute("event", attributeValue);
		return "eventForm"; // book/create.jsp
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		binder.registerCustomEditor(Category.class, new CategoryEditor(category_dao));
	}

	@RequestMapping(value = "edit-{id}", method = RequestMethod.POST)
	public String update(@ModelAttribute("event") Event event, BindingResult result, HttpSession session,
			RedirectAttributes redirectAttributes) throws AuthorizationException {
		return updateOrCreate(event, result, session, redirectAttributes);
	}

	@RequestMapping(value = "edit-{id}", method = RequestMethod.GET)
	public String updateForm(Model model, @PathVariable int id, HttpSession session) throws AuthorizationException {
		return form(model, event_dao.getById(id), session);
	}

	private String updateOrCreate(Event event, BindingResult result, HttpSession session,
			RedirectAttributes redirectAttributes) throws AuthorizationException {
		User user = user_dao.getByUsernameOrNull((String) session.getAttribute("username"));
		if (user == null) {
			throw new AuthorizationException();
		}
		event.setUser(user);
		validator.validate(event, result);

		if (result.hasErrors()) {
			return "eventForm";
		} else if (event.getEventEnd().compareTo(event.getEventStart()) < 0) {
			result.rejectValue("eventEnd", null,
					"Aplikacja nie wspiera planowania podróży w czasie. Jak coś się zaczyna to kończyć się powinno w terminie po swoim początku.");
			log.error("Nie prawidłowe dane.");
			return "eventForm";
		} else if (event.getId() == 0 && !event_dao.isDateFree(user, event.getEventStart(), event.getEventEnd())) {
			result.rejectValue("eventStart", null,
					"Nie pozwolę Ci się tak przepracowywać. W dwóch miejscach na raz człowiek być nie może.");
			log.error("Nie prawidłowe dane.");
			return "eventForm";
		}
		event_dao.saveOrUpdate(event);
		redirectAttributes.addFlashAttribute("message",
				new Message(Message.Status.SUCCESS, "Wydarzenie zostało zapisane"));
		log.info("Wydarzenie zostało zarejestrowane.");
		return "redirect:/event-" + event.getId();
	}
}