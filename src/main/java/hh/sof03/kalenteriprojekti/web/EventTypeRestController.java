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

import hh.sof03.kalenteriprojekti.domain.EventType;
import hh.sof03.kalenteriprojekti.domain.EventTypeRepository;

@CrossOrigin
@Controller
public class EventTypeRestController {

    @Autowired
    EventTypeRepository eventTypeRepository;

    @GetMapping(value = "/eventtypes")
    public @ResponseBody List<EventType> EventTypeListRest() {
        return (List<EventType>) eventTypeRepository.findAll();
    }

    @GetMapping(value = "/eventtypes/{id}")
    public @ResponseBody Optional<EventType> findEventTypeRest(@PathVariable("id") Long eventTypeId) {
        return eventTypeRepository.findById(eventTypeId);
    }

    @PostMapping(value = "/eventtypes")
    public @ResponseBody EventType saveEventTypeRest(@RequestBody EventType eventType) {
        return eventTypeRepository.save(eventType);

    }
}
