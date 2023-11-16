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

import hh.sof03.kalenteriprojekti.domain.Note;
import hh.sof03.kalenteriprojekti.domain.NoteRepository;

@CrossOrigin
@Controller
public class NoteRestController {

    @Autowired
    NoteRepository noteRepository;

    @GetMapping(value = "/notes")
    public @ResponseBody List<Note> noteListRest() {
        return (List<Note>) noteRepository.findAll();
    }

    @GetMapping(value = "/notes/{id}")
    public @ResponseBody Optional<Note> findNoteRest(@PathVariable("id") Long noteId) {
        return noteRepository.findById(noteId);
    }

    @PostMapping(value = "/notes")
    public @ResponseBody Note saveNoteRest(@RequestBody Note note) {
        return noteRepository.save(note);

    }
}
