package hh.sof03.kalenteriprojekti.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.sof03.kalenteriprojekti.domain.Event;
import hh.sof03.kalenteriprojekti.domain.EventRepository;

@CrossOrigin
@Controller
public class EventRestController {

    @Autowired
    EventRepository eventRepository;

    @GetMapping(value = "/events")
    public @ResponseBody List<Event> EventListRest() {
        return (List<Event>) eventRepository.findAll();
    }

    @GetMapping(value = "/events/{id}")
    public @ResponseBody Optional<Event> findEventRest(@PathVariable("id") Long eventId) {
        return eventRepository.findById(eventId);
    }

    @PostMapping(value = "/events")
    public @ResponseBody Event saveEventRest(@RequestBody Event event) {
        return eventRepository.save(event);

    }
}
