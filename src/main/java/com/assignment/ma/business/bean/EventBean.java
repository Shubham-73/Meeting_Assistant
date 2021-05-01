package com.assignment.ma.business.bean;

public class EventBean implements Comparable<EventBean> {

    private Float startDateTime;
    private Float endDateTime;
    private String attendee;

    public int compareTo(EventBean event) {
        if (startDateTime == event.startDateTime)
            return 0;
        else if (startDateTime > event.startDateTime)
            return 1;
        else
            return -1;
    }

    public EventBean() {
    }

    public EventBean(Float startDateTime, Float endDateTime, String attendee) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.attendee = attendee;
    }

    public Float getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Float startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Float getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Float endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getAttendee() {
        return attendee;
    }

    public void setAttendee(String attendee) {
        this.attendee = attendee;
    }

}
