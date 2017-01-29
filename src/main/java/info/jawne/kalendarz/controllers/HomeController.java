package info.jawne.kalendarz.controllers;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import info.jawne.kalendarz.dao.UserDao;

@Controller
public class HomeController {
	@Autowired
	UserDao dao;
	protected final Log log = LogFactory.getLog(getClass());

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String show(Model model, HttpSession session) {
		log.info("Strona glowna wczytana!");
		return "home";
	}

}