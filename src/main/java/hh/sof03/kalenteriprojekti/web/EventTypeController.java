package hh.sof03.kalenteriprojekti.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import hh.sof03.kalenteriprojekti.domain.EventType;
import hh.sof03.kalenteriprojekti.domain.EventTypeRepository;
import jakarta.validation.Valid;

@Controller
public class EventTypeController {

    @Autowired
    EventTypeRepository eventTypeRepository;
     @Autowired
    EvenRepository eventRepository;

    @GetMapping(value = { "/eventtypes" })
    public String showAllEventTypes(Model model) {
        List<EventType> eventtypes = (List<EventType>) eventTypeRepository.findAll();
        model.addAttribute("eventtypes", eventtypes);
        return "eventtypes";
    }

    @GetMapping(value = "/addeventtype")
    public String addEventTyåe(Model model) {
        model.addAttribute("eventtype", new EventType());
        return "addeventtype";
    }

    @GetMapping(value = "/delete-eventtype/{id}")
    public String deleteNote(@PathVariable("id") Long eventtypeId, Model model) {
        eventTypeRepository.deleteById(eventtypeId);
        return "redirect:/eventtypes";
    }

    @PostMapping(value = "/save-eventtype")
    public String saveNote(@Valid EventType eventType, BindingResult br, Model model) {
        model.addAttribute("eventtypes", eventTypeRepository.findAll());
        if (br.hasErrors()) {
            return "/addeventtype";
        } else {
            eventTypeRepository.save(eventType);
            return "redirect:/eventtypes";
        }

    }

    @GetMapping(value = "/edit-eventtype/{id}")
    public String editEventType(@PathVariable("id") Long eventtypeId, Model model) {
        Optional<EventType> eventType = eventTypeRepository.findById(eventtypeId);
        model.addAttribute("eventtype", eventType.get());
        return "/editeventtype";
    }

    @PostMapping(value = "/save-eventtype-edits")
    public String saveEventTypeEdits(@Valid @ModelAttribute("eventtype") EventType eventType, BindingResult br,
            Model model) {
        if (br.hasErrors()) {
            return "editeventtype";
        } else {
            eventTypeRepository.save(eventType);
            return "redirect:/eventtypes";
        }
    }


}
