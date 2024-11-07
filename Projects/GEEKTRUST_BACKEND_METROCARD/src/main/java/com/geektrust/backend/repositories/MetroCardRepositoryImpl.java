package com.geektrust.backend.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import com.geektrust.backend.entities.MetroCard;

public class MetroCardRepositoryImpl implements MetroCardRepository {
    private final Map<String, MetroCard> metroCardMap;
    private int autoIncrement;

    public MetroCardRepositoryImpl(Map<String, MetroCard> metroCardMap) {
        this.metroCardMap = metroCardMap;
        this.autoIncrement = metroCardMap.size();
    }

    public MetroCardRepositoryImpl() {
        this(new HashMap<>());
    }

    @Override
    public MetroCard save(MetroCard entity) {
        if (entity.getId() == null) {
            String newId = Integer.toString(++autoIncrement);
            entity = new MetroCard(newId, entity.getCardNumber(), entity.getBalance());
        }
        metroCardMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<MetroCard> findByCardNumber(String cardNumber) {
        return metroCardMap.values().stream()
            .filter(metroCard -> metroCard.getCardNumber().equals(cardNumber))
            .findFirst();
    }
}
