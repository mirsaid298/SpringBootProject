package org.example.bootproject.service;

import org.example.bootproject.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT DISTINCT e.first_team FROM Event e UNION SELECT DISTINCT e.second_team FROM Event e")
    List<String> findAllTeams();

    @Query("SELECT DISTINCT e.date FROM Event e")
    List<String> findAllEventDates();

    @Query("SELECT DISTINCT e.result FROM Event e")
    List<String> findAllResults();

    List<Event> findByDateBefore(LocalDate date);

    List<Event> findByDateAfter(LocalDate date);

    List<Event> findByDate(LocalDate date);


}