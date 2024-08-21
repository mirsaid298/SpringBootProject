package org.example.bootproject.entity;

import jakarta.persistence.*;

@Entity
public class Teams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String teamName;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Teams() {}

    public Teams(Long id, String teamName, Category category) {
        this.id = id;
        this.teamName = teamName;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
