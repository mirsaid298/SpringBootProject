package org.example.bootproject.service;

import jakarta.persistence.Id;
import org.example.bootproject.entity.Category;
import org.example.bootproject.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AdminService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    CategoryRepository categoryRepository;

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

    @Transactional
    public Event createEvent(Event event) {
        Category category = categoryRepository.findById(event.getCategory().getId()).orElse(null);

        if (category == null) {
            throw new IllegalArgumentException("Category not found");
        }

        event.setCategory(category);

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

    public Set<String> getAllTeamsByCategory(Long id) {
        Set<String> teams = new HashSet<>();
        Category category = categoryRepository.findById(id).orElse(null);

        if (category != null) {
            teams.addAll(category.getTeams());
        } else {
            throw new IllegalArgumentException("Category not found with id: " + id);
        }
        return teams;
    }
}
