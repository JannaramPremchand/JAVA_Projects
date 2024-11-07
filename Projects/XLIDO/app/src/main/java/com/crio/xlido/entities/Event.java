package com.crio.xlido.entities;

public class Event {
    private int eventId;
    private String eventName;
    private int organizerId;

    public Event(int eventId, String eventName, int organizerId) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.organizerId = organizerId;
    }

    public int getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public int getOrganizerId() {
        return organizerId;
    }
}
