package hh.sof03.kalenteriprojekti.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class EventType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventTypeId;
    private String eventTypeName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eventType")
    @JsonIgnore
    @JsonIgnoreProperties("eventtype")
    private List<Event> events;

    // konstruktorit
    public EventType() {
    }

    public EventType(String name) {
        this.eventTypeName = name;
    }

    public EventType(String name, List<Event> events) {
        this.eventTypeName = name;
        this.events = events;
    }

    public EventType(Long eventTypeId, String name, List<Event> events) {
        this.eventTypeId = eventTypeId;
        this.eventTypeName = name;
        this.events = events;
    }

    // getterit ja setterit
    public Long getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(Long eventtypeId) {
        this.eventTypeId = eventtypeId;
    }

    public String getEventTypeName() {
        return eventTypeName;
    }

    public void setEventTypeName(String name) {
        this.eventTypeName = name;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    // toString-metodi
    @Override
    public String toString() {
        return eventTypeName;
    }

}
