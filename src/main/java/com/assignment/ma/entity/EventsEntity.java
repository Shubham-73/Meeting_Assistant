package com.assignment.ma.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "Events")
@Table(name="Events")
public class EventsEntity implements Comparable<EventsEntity>{    
 
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="eventid")
    private Integer eventID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner", referencedColumnName = "ownerid")
    private CalendarEntity ownerID;

    @Column(name="startDateTime")
    private Float startDateTime;

    @Column(name="endDateTime")
    private Float endDateTime;

    @Column(name="attendee")
    private String attendee;

 

    public int compareTo(EventsEntity event)
    {
        if (startDateTime == event.startDateTime)
            return 0;
        else if (startDateTime > event.startDateTime)
            return 1;
        else
            return -1;
    }



    public Integer getEventID() {
        return eventID;
    }



    public void setEventID(Integer eventID) {
        this.eventID = eventID;
    }



    public CalendarEntity getOwnerID() {
        return ownerID;
    }



    public void setOwnerID(CalendarEntity ownerID) {
        this.ownerID = ownerID;
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
