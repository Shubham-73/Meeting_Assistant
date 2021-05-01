package com.assignment.ma.controller;

import java.util.ArrayList;
import java.util.List;

import com.assignment.ma.business.bean.CalendarBean;
import com.assignment.ma.business.bean.CalendarEventWrapper;
import com.assignment.ma.business.bean.UserUserDuration;
import com.assignment.ma.service.CalendarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeetingController {

	@Autowired
	private CalendarService calendarService;

	// Mapping for finding free slot given two user's Calendar of provided duration
	// in minutes
	@RequestMapping(value = "/free", method = RequestMethod.POST)
	public ResponseEntity<List<Float>> free(@RequestBody UserUserDuration wrapper) throws Exception {
		List<Float> freeslots = calendarService.freeBusy(wrapper);
		if (freeslots.isEmpty()) {
			return new ResponseEntity<List<Float>>(freeslots, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Float>>(freeslots, HttpStatus.FOUND);

	}

	// Mapping for viewing Conflict given a request
	@RequestMapping(value = "/conflict", method = RequestMethod.POST)
	public ResponseEntity<List<String>> conflictList(@RequestBody CalendarEventWrapper wrapper) throws Exception {
		List<String> conflicts = calendarService.conflictView(wrapper.getCalendarBean(), wrapper.getEventBean());
		if (!conflicts.isEmpty()) {
			return new ResponseEntity<List<String>>(conflicts, HttpStatus.FOUND);
		}
		List<String> empty = new ArrayList<String>();
		empty.add("No Conflicting participant found");
		return new ResponseEntity<List<String>>(empty, HttpStatus.FOUND);

	}

	// Mapping to set a meeting given owner and attendee are present in DB
	@RequestMapping(value = "/setmeet", method = RequestMethod.POST)
	public ResponseEntity<String> addMeet(@RequestBody CalendarEventWrapper wrapper) throws Exception {

		Integer id = calendarService.setMeeting(wrapper.getCalendarBean(), wrapper.getEventBean());
		if (id != 0) {
			return new ResponseEntity<String>("Meeting added successfully with id:" + id, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Owner/Atendee not found in DB", HttpStatus.NOT_FOUND);
		}

	}

	// Mappint to add new User to DB
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<String> addNewUser(@RequestBody CalendarBean user) throws Exception {

		Integer id = calendarService.addUser(user);
		if (id != 0) {
			return new ResponseEntity<String>("User added successfully with id:" + id, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("User already Present", HttpStatus.CONFLICT);
		}

	}

}
