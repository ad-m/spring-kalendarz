package info.jawne.kalendarz.controllers;

import java.util.Locale;

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
import info.jawne.kalendarz.models.Message;

@Controller
public class CategoryDeleteController {
	private final Log log = LogFactory.getLog(getClass());

	@Autowired
	private ReloadableResourceBundleMessageSource messageSource;

	@Autowired
	CategoryDao category_dao;

	@RequestMapping(value = "/categories/{id}/delete", method = RequestMethod.POST)
	public String action(Model model, @PathVariable int id, RedirectAttributes redirectAttributes) {
		category_dao.remove(category_dao.getById(id));
		redirectAttributes.addFlashAttribute("message", new Message(Message.Status.SUCCESS,
				messageSource.getMessage("CategoryFormController.saved", null, Locale.US)));
		log.info("Kategoria została usunięta..");
		return "redirect:/";
	}

	@RequestMapping(value = "/categories/{id}/delete", method = RequestMethod.GET)
	public String confirmation(Model model, @PathVariable long id) {
		model.addAttribute("category", category_dao.getById(id));
		return "categoryDelete";
	}

}