package com.geektrust.backend.services;

import com.geektrust.backend.entities.MetroCard;
import com.geektrust.backend.entities.Passenger;
import com.geektrust.backend.repositories.MetroCardRepository;

public class MetroCardServiceImpl implements MetroCardService {
    private final StationService stationService;
    private final MetroCardRepository metroCardRepository;

    public MetroCardServiceImpl(StationService stationService, MetroCardRepository metroCardRepository) {
        this.stationService = stationService;
        this.metroCardRepository = metroCardRepository;
    }

    @Override
    public MetroCard create(String cardNumber, int balance) {
        return metroCardRepository.findByCardNumber(cardNumber)
            .orElseGet(() -> {
                MetroCard metroCard = new MetroCard(cardNumber, balance);
                return metroCardRepository.save(metroCard);
            });
    }

    @Override
    public void recharge(MetroCard metroCard, int travelCharge, Passenger passenger) {
        int rechargeAmount = Math.max(0, travelCharge - metroCard.getBalance());
        metroCard.addAmount(rechargeAmount);
        stationService.collectServiceFee(passenger, rechargeAmount);
    }

    @Override
    public void makePayment(MetroCard metroCard, int travelCharge, Passenger passenger) {
        metroCard.deductAmount(travelCharge);
        stationService.collectTravelCharge(passenger, travelCharge);
    }
}
