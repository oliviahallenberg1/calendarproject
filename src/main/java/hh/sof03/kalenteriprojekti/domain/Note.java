package hh.sof03.kalenteriprojekti.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;

    // konstruktorit
    public Note() {
    }

    public Note(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Note(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    // getterit ja setterit
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    // toString
    @Override
    public String toString() {
        return "Note: id=" + id + ", title=" + title + ", description=" + description;
    }

}
