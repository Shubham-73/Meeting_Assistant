package com.assignment.ma.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.assignment.ma.business.bean.CalendarBean;
import com.assignment.ma.business.bean.EventBean;
import com.assignment.ma.business.bean.UserUserDuration;
import com.assignment.ma.entity.CalendarEntity;
import com.assignment.ma.entity.EventsEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CalendarEventDAOWrapper {

	@Autowired
	private CalendarDAO cDao;
	@Autowired
	private EventDAO eDao;

	/* --------------------------addUser-------------------------- */
	public Integer addEmployee(CalendarBean user) {
		CalendarEntity calendarEntityBean = new CalendarEntity();

		Optional<CalendarEntity> ownerByName = cDao.findOwnerByName(user.getOwner());
		if (ownerByName.isPresent()) {
			return 0;
		} else {
			calendarEntityBean.setOwner(user.getOwner());
			cDao.save(calendarEntityBean);
		}
		return calendarEntityBean.getOwnerID();

	}

	/* --------------------------Free Slot-------------------------- */
	public List<Float> freeslot(UserUserDuration wrapper) {
		List<Float> free = new ArrayList<Float>();
		List<EventBean> busy = new ArrayList<EventBean>();
		busy.addAll(wrapper.getUser_events());
		busy.addAll(wrapper.getSecond_user_events());
		Float duration = (float) (wrapper.getDuration() / 60);
		Collections.sort(busy);

		// Overlapping slots
		List<EventBean> busy_both = new ArrayList<EventBean>();
		busy_both.add(busy.get(0));
		Iterator<EventBean> it1 = busy.iterator();

		while (it1.hasNext()) {
			EventBean event = it1.next();
			if (event.getStartDateTime() < busy_both.get(busy_both.size() - 1).getEndDateTime()) {
				busy_both.get(busy_both.size() - 1).setEndDateTime(
						Math.max(event.getEndDateTime(), busy_both.get(busy_both.size() - 1).getEndDateTime()));
			} else {
				busy_both.add(event);
			}

		}

		List<EventBean> freeSlot = new ArrayList<EventBean>();
		Iterator<EventBean> it2 = busy_both.iterator();

		Float starTime = (float) 0.0;
		Float endTime = (float) 23.0;

		if (busy_both.get(0).getStartDateTime() > 0.0) {
			freeSlot.add(new EventBean((float) 0.0, busy_both.get(0).getStartDateTime(), "free"));
		}

		// Free slots
		it2.next();
		for (EventBean event : busy_both) {

			starTime = event.getEndDateTime();
			endTime = (float) 23.0;
			if (it2.hasNext()) {
				endTime = it2.next().getStartDateTime();
			}
			if ((endTime - starTime) > 0.0)
				freeSlot.add(new EventBean(starTime, endTime, "free"));
		}

		for (EventBean event : freeSlot) {
			if ((event.getEndDateTime() - event.getStartDateTime()) >= duration) {
				free.add(event.getStartDateTime());

			}
		}

		return free;
	}

	/* --------------------------Conflict View-------------------------- */
	public List<String> conflict(CalendarBean calendar, EventBean event) {
		Optional<CalendarEntity> ownerByName = cDao.findOwnerByName(calendar.getOwner());
		Optional<CalendarEntity> atendeeByName = cDao.findOwnerByName(event.getAttendee());
		List<String> conflictUsers = new ArrayList<String>();

		if (ownerByName.isPresent() && atendeeByName.isPresent()) {
			String owner = calendar.getOwner();
			Float startTime = event.getStartDateTime();
			Float endTime = event.getEndDateTime();
			String atendee = event.getAttendee();

			List<EventsEntity> hostlist = eDao.findEventByID(ownerByName.get()).get();
			List<EventsEntity> atendeelist = eDao.findEventByID(atendeeByName.get()).get();
			Collections.sort(hostlist);
			Collections.sort(atendeelist);

			boolean flag = false;
			for (EventsEntity iter : hostlist) {
				if (startTime >= iter.getStartDateTime() && startTime <= iter.getEndDateTime()) {
					conflictUsers.add(owner);
					flag = true;
					break;
				}
				if (endTime >= iter.getStartDateTime() && endTime <= iter.getEndDateTime()) {
					conflictUsers.add(owner);
					flag = true;
					break;

				}
			}
			if (!flag) {
				System.out.println(owner + " has no Conficts!");
			}

			// Atendee Conflict
			flag = false;
			for (EventsEntity iter : atendeelist) {
				if (startTime >= iter.getStartDateTime() && startTime <= iter.getEndDateTime()) {
					conflictUsers.add(atendee);
					flag = true;
					break;
				}
				if (endTime >= iter.getStartDateTime() && endTime <= iter.getEndDateTime()) {
					conflictUsers.add(atendee);
					flag = true;
					break;

				}
			}
			if (!flag) {
				System.out.println(atendee + " has No Conficts!");
			}
		}

		return conflictUsers;
	}

	/* --------------------------Set Meeting-------------------------- */
	public Integer saveMeeting(CalendarBean calendar, EventBean event) {
		CalendarEntity calendarEntityBean = new CalendarEntity();
		EventsEntity eventEntitybean = new EventsEntity();

		Optional<CalendarEntity> ownerByName = cDao.findOwnerByName(calendar.getOwner());
		Optional<CalendarEntity> atendeeByName = cDao.findOwnerByName(event.getAttendee());

		if (ownerByName.isPresent() && atendeeByName.isPresent()) {
			calendarEntityBean = ownerByName.get();
			eventEntitybean.setStartDateTime(event.getStartDateTime());
			eventEntitybean.setEndDateTime(event.getEndDateTime());
			eventEntitybean.setAttendee(event.getAttendee());
			eventEntitybean.setOwnerID(calendarEntityBean);

			EventsEntity eventsEntity = eDao.save(eventEntitybean);
			calendarEntityBean.getEvents().add(eventEntitybean);
			return eventsEntity.getEventID();

		}
		return 0;

	}

}