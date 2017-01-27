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
import info.jawne.kalendarz.controllers.commands.RegisterCommand;
import model.Message;
import model.User;

@RequestMapping("/register")
@Controller
public class RegisterFormController {
	protected final Log log = LogFactory.getLog(getClass());
	@Autowired
	UserDao dao;

	@RequestMapping(method = RequestMethod.POST)
	public String create(HttpServletRequest request, HttpServletResponse response, @Valid RegisterCommand register,
			BindingResult errors, HttpSession session, RedirectAttributes redirectAttributes) {
		if (errors.hasErrors()) {
			return "user/registerForm";
		} else if (dao.getByUsernameOrNull(register.getUsername()) != null) {
			errors.rejectValue("username", null, "Podana nazwa użytkownika jest zajęta.");
			log.error("Nie prawidłowe dane - nazwa użytkownika zajęta.");
			return "user/registerForm";
		} else if (!register.isPasswordEqual()) {
			errors.rejectValue("confirmationPassword", null, "Wprowadzone hasła różnią się.");
			log.error("Nie prawidłowe dane - hasła różnia się.");
			return "user/registerForm";
		}
		User u = new User(register.getUsername(), register.getPassword(), dao.countAll() > 0);

		log.info(u.toString());
		dao.saveOrUpdate(u);
		redirectAttributes.addFlashAttribute("message",
				new Message(Message.Status.SUCCESS, "Użytkownik zarejestrowany!"));
		log.info("Użytkownik został zarejestrowany");
		return "redirect:/";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("registerCommand", new RegisterCommand());
		return "user/registerForm"; // book/create.jsp
	}

}