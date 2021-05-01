package com.assignment.ma.service;

import java.util.List;

import com.assignment.ma.business.bean.CalendarBean;
import com.assignment.ma.business.bean.EventBean;
import com.assignment.ma.business.bean.UserUserDuration;
import com.assignment.ma.dao.CalendarEventDAOWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarServiceImpl implements CalendarService {

	@Autowired
	private CalendarEventDAOWrapper calendarEventDAOWrapper;

	public Integer addUser(CalendarBean user) {
		Integer userid = calendarEventDAOWrapper.addEmployee(user);
		return userid;
	}

	public Integer setMeeting(CalendarBean calendar, EventBean event) throws Exception {
		int meetid = calendarEventDAOWrapper.saveMeeting(calendar, event);
		return meetid;
	}

	public List<Float> freeBusy(UserUserDuration wrapper) throws Exception {
		List<Float> free = calendarEventDAOWrapper.freeslot(wrapper);
		return free;
	}

	public List<String> conflictView(CalendarBean calendar, EventBean event) throws Exception {
		return calendarEventDAOWrapper.conflict(calendar, event);
	}
}
