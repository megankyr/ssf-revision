package vttp.ssf.assessment.eventmanagement.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.repositories.RedisRepository;

@Service
public class EventService {

    @Autowired
    RedisRepository repo;

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        int index = repo.getNumberOfEvents();

        for (int i = 1; i <= index; i++) {
            Event event = repo.getEvent(i);
            events.add(event);
        }

        return events;
    }

}
