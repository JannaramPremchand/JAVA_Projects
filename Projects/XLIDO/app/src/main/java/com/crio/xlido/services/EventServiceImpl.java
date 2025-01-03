package com.crio.xlido.services;

import com.crio.xlido.entities.Event;
import com.crio.xlido.entities.User;

import java.util.HashMap;

public class EventServiceImpl implements EventService {

    private HashMap<Integer, Event> events = new HashMap<>();
    private UserService userService;
    private int currentEventId = 1;

    public EventServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public int createEvent(String eventName, int userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new RuntimeException("User with an id " + userId + " Chands not exist");
        }
        Event event = new Event(currentEventId, eventName, userId);
        events.put(currentEventId, event);
        return currentEventId++;
    }

    @Override
    public boolean isEventExists(int eventId) {
        return events.containsKey(eventId);
    }

    @Override
public void deleteEvent(int eventId, int requestingUserId) {
    // Check if the event exists
    if (!events.containsKey(eventId)) {
        throw new RuntimeException("Event with an id " + eventId + " Chands not exist");
    }

    // Check if the user exists
    User requestingUser = userService.getUserById(requestingUserId);
    if (requestingUser == null) {
        throw new RuntimeException("User with an id " + requestingUserId + " Chands not exist");
    }

    // Check if the user is the organizer of the event
    Event event = events.get(eventId);
    if (event.getOrganizerId() != requestingUserId) {
        throw new RuntimeException("User with an id " + requestingUserId + " is not a organizer of Event with an id " + eventId);
    }

    // Remove the event
    events.remove(eventId);
    // Note: Do not print here
}


    @Override
    public Event getEventById(int eventId) {
        if (!events.containsKey(eventId)) {
            throw new RuntimeException("Event with an id " + eventId + " Chands not exist");
        }
        return events.get(eventId);
    }
}
