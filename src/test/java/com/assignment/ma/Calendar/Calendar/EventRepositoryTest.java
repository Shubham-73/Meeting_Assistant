package com.assignment.ma.Calendar.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import com.assignment.ma.dao.CalendarDAO;
import com.assignment.ma.dao.EventDAO;
import com.assignment.ma.entity.CalendarEntity;
import com.assignment.ma.entity.EventsEntity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class EventRepositoryTest {

    @Autowired
    private EventDAO underTest;
    @Autowired
    private CalendarDAO underTestReq;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
        underTestReq.deleteAll();
    }

    @Test
    void itShouldCheckIfCalendarEventExists() {

        // Given
        String owner = "user7";
        CalendarEntity newUser = new CalendarEntity();
        newUser.setOwner(owner);

        underTestReq.save(newUser);

        EventsEntity newEvent = new EventsEntity();
        newEvent.setAttendee("test");
        newEvent.setStartDateTime((float) 12.0);
        newEvent.setEndDateTime((float) 15.0);
        newEvent.setOwnerID(newUser);

        underTest.save(newEvent);

        // When
        Optional<List<EventsEntity>> eventByID = underTest.findEventByID(newUser);
        boolean expected = eventByID.isPresent();

        // Then
        assertThat(expected).isTrue();
    }

    @Test
    void itShouldCheckIfEventNotExists() {

        // Given
        String owner = "user10";
        CalendarEntity newUser = new CalendarEntity();
        newUser.setOwner(owner);

        underTestReq.save(newUser);

        // When
        Optional<List<EventsEntity>> eventByID = underTest.findEventByID(newUser);
        boolean expected = eventByID.isEmpty();

        // Then
        assertThat(expected).isFalse();
    }

}
