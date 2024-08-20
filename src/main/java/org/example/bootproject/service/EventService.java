package org.example.bootproject.service;

import org.example.bootproject.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<String> getAllTeams() {
        return eventRepository.findAllTeams();
    }

    public List<String> getAllEventDates() {
        return eventRepository.findAllEventDates();
    }

    public List<String> getAllEventResults() {
        return eventRepository.findAllResults();
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> findMatchesBefore(LocalDate date) {
        return eventRepository.findByDateBefore(date);
    }

    public List<Event> findMatchesAfter(LocalDate date) {
        return eventRepository.findByDateAfter(date);
    }

    public List<Event> findMatchesToday() {
        LocalDate today = LocalDate.now();
        return eventRepository.findByDate(today);
    }

    public List<Event> findMatchesByDate(LocalDate date) {
        return eventRepository.findByDate(date);
    }
}
