package info.jawne.kalendarz.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import info.jawne.kalendarz.controllers.commands.LogonCommand;
import info.jawne.kalendarz.controllers.editors.CategoryEditor;
import info.jawne.kalendarz.dao.CategoryDao;
import info.jawne.kalendarz.dao.EventDao;
import info.jawne.kalendarz.dao.UserDao;
import info.jawne.kalendarz.models.Category;
import info.jawne.kalendarz.models.Event;
import info.jawne.kalendarz.models.Message;
import info.jawne.kalendarz.models.User;

@Controller
@RequestMapping("/events/~create")
public class EventFormController {
	private final Log log = LogFactory.getLog(getClass());

	@Autowired
	private ReloadableResourceBundleMessageSource messageSource;

	@Autowired
	private CategoryDao category_dao;

	@Autowired
	private UserDao user_dao;

	@Autowired
	private EventDao event_dao;

	@Autowired
	private LocalValidatorFactoryBean validator;

	@RequestMapping(method = RequestMethod.POST)
	public String create(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("event") Event event,
			BindingResult result, HttpSession session, RedirectAttributes redirectAttributes) {
		LogonCommand logon = (LogonCommand) session.getAttribute("logInSession");
		User user = user_dao.getByUsernameOrNull(logon.getUsername());
		// User user = user_dao.getById(3);
		event.setUser(user);
		validator.validate(event, result);
		if (result.hasErrors()) {
			return "eventForm";
		} else if (event.getEventEnd().compareTo(event.getEventStart()) < 0) {
			result.rejectValue("eventEnd", null, "Podany czas jest za późno.");
			log.error("Nie prawidłowe dane.");
			return "eventForm";
		}
		event_dao.saveOrUpdate(event);
		redirectAttributes.addFlashAttribute("message", new Message(Message.Status.SUCCESS,
				messageSource.getMessage("EventFormController.saved", null, Locale.US)));
		log.info("Wydarzenie zostało zarejestrowane.");
		return "redirect:/";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("event", new Event());
		return "eventForm"; // book/create.jsp
	}

	@RequestMapping(value = "detail-{id}", method = RequestMethod.POST)
	public String update(HttpServletRequest request, @PathVariable int id, HttpServletResponse response,
			@ModelAttribute("event") Event event, BindingResult result, HttpSession session,
			RedirectAttributes redirectAttributes) {
		LogonCommand logon = (LogonCommand) session.getAttribute("logInSession");
		User user = user_dao.getByUsernameOrNull(logon.getUsername());
		// User user = user_dao.getById(3);
		event.setUser(user);
		validator.validate(event, result);
		if (result.hasErrors()) {
			return "eventForm";
		} else if (event.getEventEnd().compareTo(event.getEventStart()) < 0) {
			result.rejectValue("eventEnd", null, "Podany czas jest za późno.");
			log.error("Nie prawidłowe dane.");
			return "eventForm";
		}
		event_dao.saveOrUpdate(event);
		redirectAttributes.addFlashAttribute("message", new Message(Message.Status.SUCCESS,
				messageSource.getMessage("EventFormController.saved", null, Locale.US)));
		log.info("Wydarzenie zostało zarejestrowane.");
		return "redirect:/";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		binder.registerCustomEditor(Category.class, new CategoryEditor(category_dao));
	}

}