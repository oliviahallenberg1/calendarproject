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

import hh.sof03.kalenteriprojekti.domain.Note;
import hh.sof03.kalenteriprojekti.domain.NoteRepository;
import jakarta.validation.Valid;

@Controller
public class NoteController {

    @Autowired
    NoteRepository noteRepository;

    @GetMapping(value = { "/allnotes" })
    public String showAllNotes(Model model) {
        List<Note> notes = (List<Note>) noteRepository.findAll();
        model.addAttribute("notes", notes);
        return "allnotes";
    }

    @GetMapping(value = "/addnote")
    public String addNote(Model model) {
        model.addAttribute("note", new Note());
        return "addnote";
    }

    @GetMapping(value = "/delete-note/{id}")
    public String deleteNote(@PathVariable("id") Long noteId, Model model) {
        noteRepository.deleteById(noteId);
        return "redirect:/calendar";
    }

    @PostMapping(value = "/save-note")
    public String saveEvent(@Valid Note note, BindingResult br, Model model) {
        model.addAttribute("notes", noteRepository.findAll());
        if (br.hasErrors()) {
            return "addnote";
        } else {

            noteRepository.save(note);
            return "redirect:/calendar";
        }
    }

    @GetMapping(value = "/edit-note/{id}")
    public String editNote(@PathVariable("id") Long noteId, Model model) {
        Optional<Note> note = noteRepository.findById(noteId);
        model.addAttribute("note", note.get());
        return "editnote";
    }

    @PostMapping(value = "/save-note-edits")
    public String saveEdits(@Valid @ModelAttribute("note") Note note, BindingResult br, Model model) {
        if (br.hasErrors()) {
            return "editnote";
        } else {
            noteRepository.save(note);
            return "redirect:/calendar";
        }

    }

}
