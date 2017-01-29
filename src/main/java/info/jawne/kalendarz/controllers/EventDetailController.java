package info.jawne.kalendarz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import info.jawne.kalendarz.dao.EventDao;
import info.jawne.kalendarz.models.Event;

@Controller
public class EventDetailController {
	@Autowired
	private EventDao event_dao;

	@RequestMapping(value = "/event-{id}", method = RequestMethod.GET)
	public String details(@PathVariable int id, Model model) {
		Event event = event_dao.getWithCategory(id);
		model.addAttribute("event", event);

		return "eventDetails";
	}

}