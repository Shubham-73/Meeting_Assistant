package com.assignment.ma.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity(name = "Calendar")
@Table(name = "Calendar")
public class CalendarEntity {

	@Column(name = "employeename")
	private String owner;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ownerid")
	private Integer ownerID;

	@OneToMany(cascade = CascadeType.ALL)
	@OrderBy("startDateTime")
	private List<EventsEntity> events;

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Integer getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(Integer ownerID) {
		this.ownerID = ownerID;
	}

	public List<EventsEntity> getEvents() {
		return events;
	}

	public void setEvents(List<EventsEntity> events) {
		this.events = events;
	}

	@Override
	public String toString() {
		return "CalendarEntity [events=" + events + ", owner=" + owner + ", ownerID=" + ownerID + "]";
	}

}
