package info.jawne.kalendarz.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import info.jawne.kalendarz.dao.EventDao;
import info.jawne.kalendarz.models.Event;

@Controller
public class EventDetailController {
	@Autowired
	private EventDao category_dao;
	@Autowired
	private EventDao event_dao;

	@RequestMapping(value = "/event-{id}", method = RequestMethod.GET)
	public String details(@PathVariable int id, Model model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, RedirectAttributes redirectAttributes) {
		Event event = event_dao.getById(id);
		model.addAttribute("event", event);
		model.addAttribute("EventCategory", category_dao.getById(event.getCategory().getId()));

		return "eventDetails";
	}

}