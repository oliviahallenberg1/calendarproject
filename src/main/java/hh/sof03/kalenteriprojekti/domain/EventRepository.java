package hh.sof03.kalenteriprojekti.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {
   // List<Event> findByEventTypeName(String name);
}
