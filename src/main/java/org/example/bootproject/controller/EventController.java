package org.example.bootproject.controller;

import org.example.bootproject.entity.Event;
import org.example.bootproject.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/list-matches")
    public ResponseEntity<?> getAllEvents(@RequestParam(name = "time-filter", required = false) String timeFilter,
                                          @RequestParam(name = "date", required = false) String dateFilter) {

        LocalDate today = LocalDate.now();
        List<Event> events;

        try {
            if (dateFilter != null) {
                LocalDate date;
                date = LocalDate.parse(dateFilter);
                events = eventService.findMatchesByDate(date);
                return ResponseEntity.ok(events);
            }
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body("Invalid date format.  Please use YYYY-MM-DD.");
        }
        if ("old".equalsIgnoreCase(timeFilter)) {
            events = eventService.findMatchesBefore(today);
        } else if ("new".equalsIgnoreCase(timeFilter)) {
            events = eventService.findMatchesAfter(today);
        } else if ("today".equalsIgnoreCase(timeFilter)) {
            events = eventService.findMatchesToday();
        } else {
            events = eventService.getAllEvents();
        }
        return ResponseEntity.ok(events);

    }

}
