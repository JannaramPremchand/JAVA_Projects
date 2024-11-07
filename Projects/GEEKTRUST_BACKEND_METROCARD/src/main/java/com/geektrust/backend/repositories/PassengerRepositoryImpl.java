package com.geektrust.backend.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import com.geektrust.backend.entities.MetroCard;
import com.geektrust.backend.entities.Passenger;

public class PassengerRepositoryImpl implements PassengerRepository {
    private final Map<String, Passenger> passengerMap;
    private int autoIncrement;

    public PassengerRepositoryImpl(Map<String, Passenger> passengerMap) {
        this.passengerMap = passengerMap;
        this.autoIncrement = passengerMap.size();
    }

    public PassengerRepositoryImpl() {
        this(new HashMap<>());
    }

    @Override
    public Passenger save(Passenger entity) {
        if (entity.getId() == null) {
            String newId = Integer.toString(++autoIncrement);
            entity = new Passenger(newId, entity.getMetroCard(), entity.getPassengerType(), entity.getBoardingStation());
        }
        passengerMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<Passenger> findByMetroCard(MetroCard metroCard) {
        return passengerMap.values().stream()
            .filter(passenger -> passenger.getMetroCard().equals(metroCard))
            .findFirst();
    }
}
