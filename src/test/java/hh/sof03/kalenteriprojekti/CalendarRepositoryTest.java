package hh.sof03.kalenteriprojekti;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import hh.sof03.kalenteriprojekti.domain.Event;
import hh.sof03.kalenteriprojekti.domain.EventRepository;
import hh.sof03.kalenteriprojekti.domain.Note;
import hh.sof03.kalenteriprojekti.domain.NoteRepository;
import hh.sof03.kalenteriprojekti.domain.User;
import hh.sof03.kalenteriprojekti.domain.UserRepository;

@DataJpaTest
public class CalendarRepositoryTest {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private UserRepository userRepository;

    // CREATE-testit

    @Test // uuden tapahtuman luonti
    public void createNewEvent() {
        Event e = new Event();
        eventRepository.save(e);
        assertThat(e.getEventId()).isNotNull();
    }

    @Test // uuden muistiinpanon luonti
    public void createNewNote() {
        Note n = new Note();
        noteRepository.save(n);
        assertThat(n.getId()).isNotNull();
    }

    @Test // uuden käyttäjän luominen
    public void createNewUser() {
        User u = new User("testuser", "testuser", "test@test.fi", "USER");
        userRepository.save(u);
        assertThat(u.getId()).isNotNull();
    }

    // DELETE-testit
    @Test // testi tapahtuman poistamista varten
    public void deleteEvent() {
        List<Event> evs = (List<Event>) eventRepository.findAll();
        eventRepository.deleteById((long) 1);
        evs = (List<Event>) eventRepository.findAll();
        assertThat(evs).hasSize(2);
    }

    @Test // testi muistiinpanon poistamista varten
    public void deleteNote() {
        List<Note> ns = (List<Note>) noteRepository.findAll();
        noteRepository.deleteById((long) 1);
        ns = (List<Note>) noteRepository.findAll();
        assertThat(ns).hasSize(1);
    }

    @Test // käyttäjän poistaminen
    public void deleteUser() {
        List<User> users = (List<User>) userRepository.findAll();
        userRepository.deleteById((long) 1);
        users = (List<User>) userRepository.findAll();
        assertThat(users).hasSize(1);
    }

    // SEARCH-testit
}
