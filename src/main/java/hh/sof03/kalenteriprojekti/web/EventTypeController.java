package hh.sof03.kalenteriprojekti.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.sof03.kalenteriprojekti.domain.EventRepository;
import hh.sof03.kalenteriprojekti.domain.EventType;
import hh.sof03.kalenteriprojekti.domain.EventTypeRepository;
import jakarta.validation.Valid;

@Controller
public class EventTypeController {

    @Autowired
    EventTypeRepository eventTypeRepository;
    @Autowired
    EventRepository eventRepository;

    @GetMapping(value = { "/alleventtypes" })
    public String showAllEventTypes(Model model) {
        List<EventType> eventtypes = (List<EventType>) eventTypeRepository.findAll();
        model.addAttribute("eventtypes", eventtypes);
        return "alleventtypes";
    }

    @GetMapping(value = "/addeventtype")
    public String addEventType(Model model) {
        model.addAttribute("eventtype", new EventType());
        return "addeventtype";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/delete-eventtype/{id}")
    public String deleteEventType(@PathVariable("id") Long eventtypeId, Model model) {
        eventTypeRepository.deleteById(eventtypeId);
        return "redirect:/alleventtypes";
    }

    @PostMapping(value = "/save-eventtype")
    public String saveEventType(@Valid EventType eventType, BindingResult br, Model model) {
        model.addAttribute("eventtype", new EventType());
        if (br.hasErrors()) {
            return "/addeventtype";
        } else {
            eventTypeRepository.save(eventType);
            return "redirect:/alleventtypes";
        }

    }

    @GetMapping(value = "/edit-eventtype/{id}")
    public String editEventType(@PathVariable("id") Long eventtypeId, Model model) {
        Optional<EventType> eventType = eventTypeRepository.findById(eventtypeId);
        model.addAttribute("eventtype", eventType.get());
        return "editeventtype";
    }

    @PostMapping(value = "/save-eventtype-edits")
    public String saveEventTypeEdits(@Valid @ModelAttribute("eventtype") EventType eventType, BindingResult br,
            Model model) {
        if (br.hasErrors()) {
            return "editeventtype";
        } else {
            eventTypeRepository.save(eventType);
            return "redirect:/alleventtypes";
        }
    }

}
