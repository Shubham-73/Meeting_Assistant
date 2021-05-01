package com.assignment.ma.dao;

import java.util.List;
import java.util.Optional;

import com.assignment.ma.entity.CalendarEntity;
import com.assignment.ma.entity.EventsEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventDAO extends JpaRepository<EventsEntity, Integer> {

    @Query("SELECT e FROM Events e where e.ownerID = ?1")
    Optional<List<EventsEntity>> findEventByID(CalendarEntity id);
}
