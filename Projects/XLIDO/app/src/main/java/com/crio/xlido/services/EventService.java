package com.crio.xlido.services;

import com.crio.xlido.entities.Event;

public interface EventService {
    int createEvent(String eventName, int userId);

    boolean isEventExists(int eventId);

    void deleteEvent(int eventId, int requestingUserId);

    Event getEventById(int eventId);
}
