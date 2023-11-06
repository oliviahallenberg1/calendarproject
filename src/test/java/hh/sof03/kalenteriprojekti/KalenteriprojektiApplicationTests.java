package hh.sof03.kalenteriprojekti;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import hh.sof03.kalenteriprojekti.web.EventController;
import hh.sof03.kalenteriprojekti.web.NoteController;

@SpringBootTest
class KalenteriprojektiApplicationTests {

	@Autowired
	EventController eventController;
	@Autowired
	NoteController noteController;

	@Test
	void contextLoads() {
		assertThat(eventController).isNotNull();
		assertThat(noteController).isNotNull();
	}

}
