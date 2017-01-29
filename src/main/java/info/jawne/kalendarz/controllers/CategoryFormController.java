package info.jawne.kalendarz.controllers;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import info.jawne.kalendarz.dao.CategoryDao;
import info.jawne.kalendarz.dao.UserDao;
import info.jawne.kalendarz.exceptions.AuthorizationException;
import info.jawne.kalendarz.models.Category;
import info.jawne.kalendarz.models.Message;
import info.jawne.kalendarz.models.User;

@Controller
@RequestMapping(value = "/categories")
public class CategoryFormController {
	private final Log log = LogFactory.getLog(getClass());
	@Autowired
	CategoryDao category_dao;

	@Autowired
	private ReloadableResourceBundleMessageSource messageSource;

	@Autowired
	UserDao user_dao;

	@Autowired
	LocalValidatorFactoryBean validator;

	@RequestMapping(method = RequestMethod.POST)
	public String create(Model model, @ModelAttribute("category") Category category, HttpSession session,
			BindingResult result, RedirectAttributes redirectAttributes) throws AuthorizationException {
		return updateOrCreate(category, session, result, redirectAttributes);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String createForm(Model model, HttpSession session) throws AuthorizationException {
		return form(model, new Category(), session);
	}

	private String form(Model model, Category c, HttpSession session) throws AuthorizationException {
		User user = user_dao.getByUsernameOrNull((String) session.getAttribute("username"));
		if (user == null) {
			throw new AuthorizationException();
		}
		model.addAttribute("category", c);
		return "categoryForm";
	}

	@RequestMapping(value = "{id}", method = RequestMethod.POST)
	public String update(Model model, @ModelAttribute("category") Category category, HttpSession session,
			BindingResult result, RedirectAttributes redirectAttributes) throws AuthorizationException {
		return updateOrCreate(category, session, result, redirectAttributes);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String updateForm(Model model, @PathVariable Integer id, HttpSession session) throws AuthorizationException {
		return form(model, category_dao.getById(id), session);
	}

	private String updateOrCreate(Category category, HttpSession session, BindingResult result,
			RedirectAttributes redirectAttributes) throws AuthorizationException {
		User user = user_dao.getByUsernameOrNull((String) session.getAttribute("username"));
		if (user == null) {
			throw new AuthorizationException();
		}
		category.setUser(user);
		validator.validate(category, result);
		if (result.hasErrors()) {
			return "categoryForm";
		}
		category_dao.saveOrUpdate(category);
		redirectAttributes.addFlashAttribute("message", new Message(Message.Status.SUCCESS,
				messageSource.getMessage("CategoryFormController.saved", null, Locale.US)));
		log.info("Kategoria zostało zarejestrowana.");
		return "redirect:/";
	}

}