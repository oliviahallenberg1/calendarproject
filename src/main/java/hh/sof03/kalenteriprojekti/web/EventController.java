package hh.sof03.kalenteriprojekti.web;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.sof03.kalenteriprojekti.domain.Event;
import hh.sof03.kalenteriprojekti.domain.EventRepository;
import hh.sof03.kalenteriprojekti.domain.EventTypeRepository;
import hh.sof03.kalenteriprojekti.domain.Note;
import hh.sof03.kalenteriprojekti.domain.NoteRepository;

@Controller
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    EventTypeRepository eventTypeRepository;

    @GetMapping(value = { "/", "/calendar" })
    public String showAllEvents(Model model) {
        model.addAttribute("standardDate", new Date());
        List<Event> events = (List<Event>) eventRepository.findAll();
        model.addAttribute("events", events);
        List<Note> notes = (List<Note>) noteRepository.findAll();
        model.addAttribute("notes", notes);
        return "calendar";
    }

    @GetMapping(value = "/allmonths")
    public String showAllMonths(Model model) {
        model.addAttribute("standardDate", new Date());
        List<Event> events = (List<Event>) eventRepository.findAll();
        model.addAttribute("events", events);
        List<Note> notes = (List<Note>) noteRepository.findAll();
        model.addAttribute("notes", notes);
        return "allmonths";
    }

    @GetMapping(value = "/addevent")
    public String addEvent(Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("eventtypes", eventTypeRepository.findAll());
        return "addevent";
    }

    @PostMapping(value = "/save-event")
    public String saveEvent(@ModelAttribute Event event) {
        eventRepository.save(event);
        return "redirect:/calendar";
    }

    @GetMapping(value = "/edit-event/{id}")
    public String editEvent(@PathVariable("id") Long eventId, Model model) {
        Optional<Event> event = eventRepository.findById(eventId);
        model.addAttribute("event", event.get());
        model.addAttribute("eventtypes", eventTypeRepository.findAll());
        return "/editevent";
    }

    @PostMapping(value = "/save-event-edits")
    public String saveEdits(@ModelAttribute("event") Event event) {
        eventRepository.save(event);
        return "redirect:/calendar";
    }

    @GetMapping(value = "/delete-event/{id}")
    public String deleteEvent(@PathVariable("id") Long eventId, Model model) {
        eventRepository.deleteById(eventId);
        return "redirect:/calendar";
    }

}
