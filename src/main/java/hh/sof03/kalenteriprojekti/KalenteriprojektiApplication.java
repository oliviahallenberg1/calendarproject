package hh.sof03.kalenteriprojekti;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.sof03.kalenteriprojekti.domain.Event;
import hh.sof03.kalenteriprojekti.domain.EventRepository;
import hh.sof03.kalenteriprojekti.domain.EventType;
import hh.sof03.kalenteriprojekti.domain.EventTypeRepository;
import hh.sof03.kalenteriprojekti.domain.Note;
import hh.sof03.kalenteriprojekti.domain.NoteRepository;
import hh.sof03.kalenteriprojekti.domain.User;
import hh.sof03.kalenteriprojekti.domain.UserRepository;

@SpringBootApplication
public class KalenteriprojektiApplication {

	private static final Logger log = LoggerFactory.getLogger(KalenteriprojektiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KalenteriprojektiApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(EventRepository eventRepository, EventTypeRepository eventTypeRepository,
			NoteRepository noteRepository, UserRepository userRepository) {

		return (args) -> {
			EventType et1 = new EventType("Työt");
			EventType et2 = new EventType("Koulu");
			EventType et3 = new EventType("Muu");

			eventTypeRepository.save(et1);
			eventTypeRepository.save(et2);
			eventTypeRepository.save(et3);

			SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

			Event e1 = new Event("Työvuoro", "posti", df.parse("01.10.2023"), df.parse("01.10.2023"), "14:30", "20:30",
					et1);
			Event e2 = new Event("Backend-lopputyö", "lopputyön palautus", df.parse("07.10.2023"),
					df.parse("07.10.2023"), "12:00", "12:00", et2);
			Event e3 = new Event("Illallinen", "syömään kavereiden kanssa", df.parse("25.10.2023"),
					df.parse("25.10.2023"), "18:00", "22:00", et3);

			eventRepository.save(e1);
			eventRepository.save(e2);
			eventRepository.save(e3);

			Note n1 = new Note("Muistutus", "tee läksyt");
			Note n2 = new Note("TODO", "Lisää lopputyö gittiin");
			noteRepository.save(n1);
			noteRepository.save(n2);

			log.info("fetch all eventtypes");
			for (EventType eventType : eventTypeRepository.findAll()) {
				log.info(eventType.toString());
			}

			log.info("fetch all events");
			for (Event event : eventRepository.findAll()) {
				log.info(event.toString());
			}
			log.info("fetch all notes");
			for (Note note : noteRepository.findAll()) {
				log.info(note.toString());
			}

			// uusien käyttäjien luominen
			// user/user, admin/admin
			User u1 = new User("user", "$2a$10$a/LV/P3EOlUGEbJO8CxOSO.wgojxa/O4lXl20yfXnDcGZfzmyN5Im", "user@user.fi",
					"USER");
			User u2 = new User("admin", "$2a$10$3kt2yplsNCuDVEL6b25XXeuwyybN4LmVG0RrvlX4Zj0U2uEHCuqL2",
					"admin@admin.com", "ADMIN");

			userRepository.save(u1);
			userRepository.save(u2);

		};
	}

}
