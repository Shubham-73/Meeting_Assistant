package com.assignment.ma.dao;

import java.util.Optional;

import com.assignment.ma.entity.CalendarEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CalendarDAO extends JpaRepository<CalendarEntity, Integer> {

    @Query("SELECT o FROM Calendar o where o.owner = ?1")
    Optional<CalendarEntity> findOwnerByName(String owner);
}
