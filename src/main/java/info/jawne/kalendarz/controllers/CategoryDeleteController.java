package info.jawne.kalendarz.controllers;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import info.jawne.kalendarz.dao.CategoryDao;
import info.jawne.kalendarz.dao.UserDao;
import info.jawne.kalendarz.exceptions.AuthorizationException;
import info.jawne.kalendarz.models.Message;
import info.jawne.kalendarz.models.User;

@Controller
public class CategoryDeleteController {
	@Autowired
	CategoryDao category_dao;

	private final Log log = LogFactory.getLog(getClass());

	@Autowired
	private ReloadableResourceBundleMessageSource messageSource;

	@Autowired
	private UserDao user_dao;

	@RequestMapping(value = "/categories/{id}/delete", method = RequestMethod.POST)
	public String action(Model model, @PathVariable int id, RedirectAttributes redirectAttributes, HttpSession session)
			throws AuthorizationException {
		User user = user_dao.getByUsernameOrNull((String) session.getAttribute("username"));
		if (user == null) {
			throw new AuthorizationException();
		}
		category_dao.remove(category_dao.getById(id));
		redirectAttributes.addFlashAttribute("message", new Message(Message.Status.SUCCESS,
				messageSource.getMessage("CategoryFormController.saved", null, Locale.US)));
		log.info("Kategoria została usunięta..");
		return "redirect:/";
	}

	@RequestMapping(value = "/categories/{id}/delete", method = RequestMethod.GET)
	public String confirmation(Model model, @PathVariable long id, HttpSession session) throws AuthorizationException {
		User user = user_dao.getByUsernameOrNull((String) session.getAttribute("username"));
		if (user == null) {
			throw new AuthorizationException();
		}
		model.addAttribute("category", category_dao.getById(id));
		return "categoryDelete";
	}

}