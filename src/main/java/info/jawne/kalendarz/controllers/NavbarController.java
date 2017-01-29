package info.jawne.kalendarz.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import info.jawne.kalendarz.controllers.commands.LogonCommand;
import info.jawne.kalendarz.dao.CategoryDao;
import info.jawne.kalendarz.dao.EventDao;
import info.jawne.kalendarz.dao.UserDao;
import info.jawne.kalendarz.models.User;

@ControllerAdvice
class NavbarController {

	@Autowired
	CategoryDao category_dao;

	@Autowired
	EventDao event_dao;

	@Autowired
	UserDao user_dao;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.setBindEmptyMultipartFiles(false);
	}

	@ModelAttribute
	public void populateModel(HttpSession session, Model model) {
		LogonCommand logon = (LogonCommand) session.getAttribute("logInSession");

		if (logon == null) {
			return;
		}

		User user = user_dao.getByUsernameOrNull(logon.getUsername());
		if (user == null) {
			return;
		}
		model.addAttribute("user", user);

		model.addAttribute("userCategories", category_dao.forUser(user));
	}
}