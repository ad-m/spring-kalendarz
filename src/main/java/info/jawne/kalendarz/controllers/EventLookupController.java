package info.jawne.kalendarz.controllers;

import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.Date;
import java.util.List;

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
import info.jawne.kalendarz.dao.CategoryDao;
import info.jawne.kalendarz.dao.EventDao;
import info.jawne.kalendarz.dao.PeriodList;
import info.jawne.kalendarz.exceptions.AuthorizationException;
import info.jawne.kalendarz.models.Category;
import info.jawne.kalendarz.models.Message;

@Controller
@RequestMapping("/lookup")
public class EventLookupController {
	private final Log log = LogFactory.getLog(getClass());

	@Autowired
	private EventDao event_dao;
	@Autowired
	private CategoryDao category_dao;

	private PeriodList period_list = new PeriodList();

	@RequestMapping(method = RequestMethod.GET)
	public String form(Model model) throws AuthorizationException {

		model.addAttribute("eventLookupCommand", new EventLookupCommand());
		return "eventLookupForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String onSubmit(Model model, HttpServletRequest request, HttpServletResponse response,
			@Valid EventLookupCommand logon, BindingResult errors, HttpSession session,
			RedirectAttributes redirectAttributes) {

		if (errors.hasErrors()) {

			return "eventLookupForm";

		} else {
			log.info("Wydarzenie zostało utworzone");
			redirectAttributes.addFlashAttribute("message", new Message(Message.Status.SUCCESS, "Witaj świecie!"));

			return "redirect:/";

		}
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		binder.registerCustomEditor(Category.class, new CategoryEditor(category_dao));
	}

	@ModelAttribute("categories")
	public List<Category> load_categories() {
		return category_dao.getAll();
	}

	@ModelAttribute("periods")
	public List<Period> load_periods() {
		return period_list.asList();
	}

}
