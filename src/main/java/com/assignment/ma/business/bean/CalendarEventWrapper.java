package com.assignment.ma.business.bean;

public class CalendarEventWrapper {

    private CalendarBean calendarBean;
    private EventBean eventBean;

    public CalendarEventWrapper() {
    }

    public CalendarEventWrapper(CalendarBean calendarBean, EventBean eventBean) {
        this.calendarBean = calendarBean;
        this.eventBean = eventBean;
    }

    public CalendarBean getCalendarBean() {
        return calendarBean;
    }

    public EventBean getEventBean() {
        return eventBean;
    }

    @Override
    public String toString() {
        return "CalendarEventWrapper [calendarBean=" + calendarBean + ", eventBean=" + eventBean + "]";
    }

}
