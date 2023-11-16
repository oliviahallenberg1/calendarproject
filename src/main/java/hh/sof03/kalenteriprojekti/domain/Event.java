package hh.sof03.kalenteriprojekti.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventId;
    @NotEmpty
    private String title;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
   
    private Date startDate;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    
    private String startTime;
  
    private String endTime;
    @ManyToOne
    @JsonIgnoreProperties("events")
    @JoinColumn(name = "eventtypeid")
    private EventType eventType;

    // konstruktorit
    public Event() {
    }

    public Event(Long eventId, String title, String description, Date startDate, Date endDate, String startTime,
            String endTime, EventType eventType) {
        this.eventId = eventId;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventType = eventType;
    }

    public Event(String title, String description, Date startDate, Date endDate, String startTime, String endTime,
            EventType eventType) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventType = eventType;
    }

    // getterit ja setterit

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    // toString-metodi
    @Override
    public String toString() {
        return "Event [eventId=" + eventId + ", title=" + title + ", description=" + description + ", startDate="
                + startDate + ", endDate=" + endDate + ", eventType=" + eventType + "]";
    }

}
