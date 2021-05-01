package com.assignment.ma.Calendar.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import com.assignment.ma.dao.CalendarDAO;
import com.assignment.ma.entity.CalendarEntity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CalendarRepositoryTest {

    @Autowired
    private CalendarDAO underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckIfCalendarOwnerExists() {

        // Given
        String owner = "user7";
        CalendarEntity newUser = new CalendarEntity();
        newUser.setOwner(owner);

        underTest.save(newUser);

        // When
        Optional<CalendarEntity> ownerByName = underTest.findOwnerByName(owner);
        boolean exists = ownerByName.isPresent();

        // Then
        assertThat(exists).isTrue();
    }

    @Test
    void itShouldCheckIfCalendarOwnerNotExists() {

        // Given
        String owner = "user10";

        // When
        Optional<CalendarEntity> ownerByName = underTest.findOwnerByName(owner);
        boolean exists = ownerByName.isPresent();

        // Then
        assertThat(exists).isFalse();
    }

}
