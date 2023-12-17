package vttp.ssf.assessment.eventmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import vttp.ssf.assessment.eventmanagement.repositories.RedisRepository;
import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.models.User;

@Controller
@RequestMapping
public class RegistrationController {

    @Autowired
    RedisRepository repo;

    @GetMapping("/events/register/{eventId}")
    public ModelAndView register(@PathVariable Integer eventId, HttpSession session) {
        ModelAndView mav = new ModelAndView("eventregister");
        Event event = repo.getEvent(eventId);
        session.setAttribute("eventId", eventId);
        mav.addObject("event", event);
        mav.addObject("user", new User());
        return mav;

    }

    @PostMapping("/registration/register")
    public String processRegistration(@Valid @ModelAttribute User user, BindingResult binding, Model model,
            HttpSession session) {
                Integer eventId = (Integer) session.getAttribute("eventId");
        Event event = repo.getEvent(eventId);
        if (binding.hasErrors()) {
            model.addAttribute("event", event);
            return "eventregister";
        }

        if (!user.isValidAge()) {
            model.addAttribute("error", "You must be 21 years and above to register");
            return "errorregistration";
        }
        int demand = user.getTicketno();
        int supply = event.getEventSize() - event.getParticipants();
        if (supply < demand) {
            model.addAttribute("error", "Your request for tickets has exceeded the available slots");
            return "errorregistration";
        }

        int newParticipants = event.getParticipants() + user.getTicketno();
        repo.updateParticipants(eventId, newParticipants);
        model.addAttribute("event", event);
        return "successregistration";
    }

}