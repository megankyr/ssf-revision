package vttp.ssf.assessment.eventmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp.ssf.assessment.eventmanagement.repositories.RedisRepository;
import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.models.User;

@Controller
@RequestMapping("/events")
public class RegistrationController {

    @Autowired
    RedisRepository repo;

    @GetMapping("/register/{eventId}")
    public String register(@PathVariable Integer eventId, Model model) {
        Event event = repo.getEvent(eventId);
        model.addAttribute("event", event);
        model.addAttribute("user", new User());
        return "eventregister";
    }

}