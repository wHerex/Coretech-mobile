package com.example.coretech_mobile.calendar;

public class Event {

    private String id;
    private String subject;
    private String startDateTime;
    private long eventLength;
    private String eventDescription;

    public Event() {
    }

    public Event(String id, String subject, String startDateTime, long eventLength, String eventDescription) {
        this.id = id;
        this.subject = subject;
        this.startDateTime = startDateTime;
        this.eventLength = eventLength;
        this.eventDescription = eventDescription;
    }

    public Event(String subject, String startDateTime, long eventLength, String eventDescription) {
        this.subject = subject;
        this.startDateTime = startDateTime;
        this.eventLength = eventLength;
        this.eventDescription = eventDescription;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public long getEventLength() {
        return eventLength;
    }

    public void setEventLength(long eventLength) {
        this.eventLength = eventLength;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }
}
