package info.jawne.kalendarz.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dao.UserDao;
import info.jawne.kalendarz.controllers.commands.LogonCommand;
import model.Message;

@Controller
public class LogonFormController {

	protected final Log log = LogFactory.getLog(getClass());

	@Autowired
	UserDao dao;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	protected String showForm(HttpServletRequest request, Model model) {

		LogonCommand logon = new LogonCommand();

		model.addAttribute("logonCommand", logon);

		return "user/loginForm";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	protected String logout(HttpSession session) {
		session.removeAttribute("logInSession");
		log.info("Log out");
		return "redirect:/";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	protected String onSubmit(Model model, HttpServletRequest request, HttpServletResponse response,
			@Valid LogonCommand logon, BindingResult errors, HttpSession session,
			RedirectAttributes redirectAttributes) {

		if (errors.hasErrors()) {
			return "user/loginForm";
		} else if (!dao.checkUser(logon.getUsername(), logon.getPassword())) {
			errors.rejectValue("username", null, "Nie ma uzytkownika o takim nazwie i hasle.");
			log.error("Nie prawidłowe dane.");
			return "user/loginForm";
		} else {
			log.info("Użytkownik został zalogowany");
			redirectAttributes.addFlashAttribute("message", new Message(Message.Status.SUCCESS, "Witaj świecie!"));
			session.setAttribute("logInSession", logon);
			return "redirect:/";
		}
	}
}
