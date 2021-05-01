package com.assignment.ma.service;

import java.util.List;

import com.assignment.ma.business.bean.CalendarBean;
import com.assignment.ma.business.bean.EventBean;
import com.assignment.ma.business.bean.UserUserDuration;

public interface CalendarService {

	Integer addUser(CalendarBean user);

	Integer setMeeting(CalendarBean calendar, EventBean event) throws Exception;

	List<Float> freeBusy(UserUserDuration wrapper) throws Exception;

	List<String> conflictView(CalendarBean calendar, EventBean event) throws Exception;

}