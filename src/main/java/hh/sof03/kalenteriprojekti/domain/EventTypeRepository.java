package hh.sof03.kalenteriprojekti.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EventTypeRepository extends CrudRepository<EventType, Long> {
    //List<Event> findByEventTypeName(String eventTypeName);
}
