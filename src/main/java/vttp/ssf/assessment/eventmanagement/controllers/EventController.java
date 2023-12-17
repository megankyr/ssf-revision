package vttp.ssf.assessment.eventmanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.services.EventService;

@Controller
@RequestMapping("/events")
public class EventController {

	@Autowired
	EventService eventService;

	// handles the GET request to display events page
	@GetMapping("/listing")
	public ModelAndView displayEvents() {
		List<Event> events = eventService.getAllEvents();
		ModelAndView mav = new ModelAndView("view0");
		mav.addObject("events", events);

		return mav;
	}

}
