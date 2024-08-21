package org.example.bootproject.service;

import org.example.bootproject.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByDateBefore(LocalDate date);

    List<Event> findByDateAfter(LocalDate date);

    List<Event> findByDate(LocalDate date);

    //DONE Сделать entity отдельный для команд Teams и получается что и у тимс и у ивентов будут категории
    //DONE Сделать проверку чтобы нельзя было ввести две одиннавые команды друг против друга
    //DONE В ивенте нельзя ввести команду из категории А в категорию Б
    //DONE Сделать поиск по категории тоесть
    //DONE В репозитории findByCategory


}