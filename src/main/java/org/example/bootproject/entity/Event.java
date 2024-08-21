package org.example.bootproject.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String first_team;
    private String second_team;
    private LocalDate date;
    private String result;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Event() {}

    public Event(Long id, String first_team, String second_team, LocalDate date, String result, Category category) {
        this.id = id;
        this.first_team = first_team;
        this.second_team = second_team;
        this.date = date;
        this.result = result;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_team() {
        return first_team;
    }

    public void setFirst_team(String first_team) {
        this.first_team = first_team;
    }

    public String getSecond_team() {
        return second_team;
    }

    public void setSecond_team(String second_team) {
        this.second_team = second_team;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
