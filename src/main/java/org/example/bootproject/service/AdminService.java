package org.example.bootproject.service;

import org.example.bootproject.entity.Category;
import org.example.bootproject.entity.Event;
import org.example.bootproject.entity.Teams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    TeamsRepository teamsRepository;

    @Transactional
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Transactional
    public void removeCategory(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null) {
            categoryRepository.delete(category);
        } else {
            throw new IllegalArgumentException("Category not found with id: " + id);
        }
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    private Teams saveTeam(String teamName, Category category) {
        Teams team = new Teams();
        team.setTeamName(teamName);
        team.setCategory(category);
        return teamsRepository.save(team);
    }

    private Teams findOrCreateTeam(String teamName, Category category) {
        return teamsRepository.findByTeamName(teamName).orElseGet(() -> saveTeam(teamName, category));
    }

    @Transactional
    public Event createEvent(Event event, String firstTeamName, String secondTeamName, Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);

        if (category == null) {
            throw new IllegalArgumentException("Category not found");
        }

        Teams firstTeam = findOrCreateTeam(firstTeamName, category);
        Teams secondTeam = findOrCreateTeam(secondTeamName, category);

        if (firstTeamName.equalsIgnoreCase(secondTeamName)) {
            throw new IllegalArgumentException("First team name cannot be the same as second team name");
        }

        if (!firstTeam.getCategory().equals(secondTeam.getCategory())) {
            throw new IllegalArgumentException("First and Second teams must be in the same categories");
        }

        event.setCategory(firstTeam.getCategory());
        event.setDate(event.getDate());
        event.setResult(event.getResult());

        eventRepository.save(event);
        return event;
    }

    @Transactional
    public Event updateEvent(Long id, Event event) {
        Event existingEvent = eventRepository.findById(id).orElse(null);
        if (existingEvent == null) {
            throw new IllegalArgumentException("Event not found");
        }

        existingEvent.setFirst_team(event.getFirst_team());
        existingEvent.setSecond_team(event.getSecond_team());
        existingEvent.setDate(event.getDate());
        existingEvent.setResult(event.getResult());
        existingEvent.setCategory(event.getCategory());

        if (event.getFirst_team().equalsIgnoreCase(event.getSecond_team())) {
            throw new IllegalArgumentException("First team cannot be the same as second team");
        }

        if (event.getCategory() != null && existingEvent.getCategory() != null) {
            if (!event.getCategory().equals(existingEvent.getCategory())) {
                throw new IllegalArgumentException("First and Second teams must be in the same categories");
            }
        }

        eventRepository.save(existingEvent);
        return existingEvent;
    }

    @Transactional
    public void deleteEvent(Long id) {
        Event existingEvent = eventRepository.findById(id).orElse(null);
        if (existingEvent == null) {
            throw new IllegalArgumentException("Event not found");
        }

        eventRepository.delete(existingEvent);
    }

    public List<Teams> findTeamsByCategoryId(Long categoryId) {
        Teams teams = teamsRepository.findById(categoryId).orElse(null);

        if (teams == null) {
            throw new IllegalArgumentException("Category not found");
        }
        return teamsRepository.findTeamsByCategoryId(categoryId);
    }
}
