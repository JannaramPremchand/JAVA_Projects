package com.geektrust.backend.services;

import com.geektrust.backend.entities.MetroCard;
import com.geektrust.backend.entities.Passenger;
import com.geektrust.backend.entities.PassengerType;
import com.geektrust.backend.exceptions.MetroCardNotFoundException;
import com.geektrust.backend.repositories.MetroCardRepository;
import com.geektrust.backend.repositories.PassengerRepository;

public class PassengerServiceImpl implements PassengerService {
    private final StationService stationService;
    private final MetroCardService metroCardService;
    private final MetroCardRepository metroCardRepository;
    private final PassengerRepository passengerRepository;

    public PassengerServiceImpl(StationService stationService, MetroCardService metroCardService, 
                                 MetroCardRepository metroCardRepository, PassengerRepository passengerRepository) {
        this.stationService = stationService;
        this.metroCardService = metroCardService;
        this.metroCardRepository = metroCardRepository;
        this.passengerRepository = passengerRepository;
    }

    @Override
    public Passenger create(String cardNumber, PassengerType passengerType, String boardingStation) {
        MetroCard metroCard = metroCardRepository.findByCardNumber(cardNumber)
            .orElseThrow(MetroCardNotFoundException::new);

        return passengerRepository.findByMetroCard(metroCard)
            .map(passenger -> {
                passenger.setBoardingStation(boardingStation);
                return passenger;
            })
            .orElseGet(() -> {
                Passenger newPassenger = new Passenger(metroCard, passengerType, boardingStation);
                return passengerRepository.save(newPassenger);
            });
    }

    @Override
    public void travel(Passenger passenger) {
        MetroCard metroCard = passenger.getMetroCard();
        passenger.updateJourneyTypeCode();
        int travelCharge = stationService.getTravelCharge(passenger);

        if (metroCard.hasSufficientBalance(travelCharge)) {
            metroCardService.makePayment(metroCard, travelCharge, passenger);
        } else {
            metroCardService.recharge(metroCard, travelCharge, passenger);
            metroCardService.makePayment(metroCard, travelCharge, passenger);
        }
        
        stationService.addPassengerToBoardedList(passenger);
    }
}
