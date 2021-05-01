package com.assignment.ma.business.bean;

import java.util.List;

public class UserUserDuration {

    List<EventBean> user_events;
    List<EventBean> second_user_events;
    Integer duration;

    public UserUserDuration() {
    }

    public UserUserDuration(List<EventBean> user_events, List<EventBean> second_user_events, Integer duration) {
        this.user_events = user_events;
        this.second_user_events = second_user_events;
        this.duration = duration;
    }

    public List<EventBean> getUser_events() {
        return user_events;
    }

    public void setUser_events(List<EventBean> user_events) {
        this.user_events = user_events;
    }

    public List<EventBean> getSecond_user_events() {
        return second_user_events;
    }

    public void setSecond_user_events(List<EventBean> second_user_events) {
        this.second_user_events = second_user_events;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

}
