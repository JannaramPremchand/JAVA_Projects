package com.geektrust.backend.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.geektrust.backend.entities.Station;

public class StationRepositoryImpl implements StationRepository {
    private final Map<String, Station> stationMap;
    private int autoIncrement;

    public StationRepositoryImpl(Map<String, Station> stationMap) {
        this.stationMap = stationMap;
        this.autoIncrement = stationMap.size();
    }

    public StationRepositoryImpl() {
        this(new HashMap<>());
    }

    @Override
    public Station save(Station entity) {
        if (entity.getId() == null) {
            String newId = Integer.toString(++autoIncrement);
            entity = new Station(newId, entity.getName());
        }
        stationMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<Station> findByName(String name) {
        return stationMap.values().stream()
            .filter(station -> station.getName().equals(name))
            .findFirst();
    }

    @Override
    public List<Station> findAll() {
        return stationMap.values().stream().collect(Collectors.toList());
    }
}
