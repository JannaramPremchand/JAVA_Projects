package com.geektrust.backend.services;

import java.util.*;
import java.util.stream.Collectors;

import com.geektrust.backend.dtos.CollectionSummary;
import com.geektrust.backend.dtos.PassengerSummary;
import com.geektrust.backend.dtos.PassengerTypeCount;
import com.geektrust.backend.entities.JourneyType;
import com.geektrust.backend.entities.Passenger;
import com.geektrust.backend.entities.PassengerType;
import com.geektrust.backend.entities.Station;
import com.geektrust.backend.entities.TravelCharge;
import com.geektrust.backend.exceptions.StationNotFoundException;
import com.geektrust.backend.repositories.StationRepository;

public class StationServiceImpl implements StationService {
    private final StationRepository stationRepository;

    public StationServiceImpl(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Override
    public Station create(String stationName) {
        return stationRepository.findByName(stationName)
            .orElseGet(() -> stationRepository.save(new Station(stationName)));
    }

    @Override
    public void addPassengerToBoardedList(Passenger passenger) {
        Station station = getStation(passenger.getBoardingStation());
        station.addPassenger(new Passenger(passenger));
    }

    @Override
    public void collectTravelCharge(Passenger passenger, int travelCharge) {
        Station station = getStation(passenger.getBoardingStation());
        station.addTravelCharge(travelCharge);
    }

    @Override
    public void collectServiceFee(Passenger passenger, int rechargeAmount) {
        Station station = getStation(passenger.getBoardingStation());
        int serviceFee = calculateServiceFee(rechargeAmount);
        station.addServiceFee(serviceFee);
    }

    private int calculateServiceFee(int rechargeAmount) {
        return (int) (0.02 * rechargeAmount);
    }

    @Override
    public List<Station> getAllStations() {
        List<Station> stations = stationRepository.findAll();
        Collections.sort(stations);
        return stations;
    }

    @Override
    public int getTravelCharge(Passenger passenger) {
        int travelCharge = TravelCharge.valueOf(passenger.getPassengerType().toString()).getCharge();
        int journeyTypeCode = passenger.getJourneyTypeCode();

        if (getJourneyType(journeyTypeCode) == JourneyType.RETURN) {
            int revisedCharge = getRevisedTravelCharge(travelCharge);
            collectDiscount(passenger, travelCharge - revisedCharge);
            return revisedCharge;
        }
        return travelCharge;
    }

    @Override
    public CollectionSummary getCollectionSummary(Station station) {
        return new CollectionSummary(station.getName(), station.getTotalCollection(), station.getDiscountCollection());
    }

    @Override
    public PassengerSummary getPassengerSummary(Station station) {
        Map<PassengerType, Integer> passengerTypeCountMap = getCountPassengerTypeWise(station.getBoardedPassengers());
        List<PassengerTypeCount> passengerTypeCounts = passengerTypeCountMap.entrySet().stream()
            .map(entry -> new PassengerTypeCount(entry.getKey(), entry.getValue()))
            .sorted()
            .collect(Collectors.toList());
        return new PassengerSummary(passengerTypeCounts);
    }

    private Map<PassengerType, Integer> getCountPassengerTypeWise(List<Passenger> passengers) {
        Map<PassengerType, Integer> passengerTypeCountMap = new HashMap<>();

        for (Passenger passenger : passengers) {
            passengerTypeCountMap.merge(passenger.getPassengerType(), 1, Integer::sum);
        }
        return passengerTypeCountMap;
    }

    private JourneyType getJourneyType(int journeyTypeCode) {
        return journeyTypeCode == 0 ? JourneyType.SINGLE : JourneyType.RETURN;
    }

    private int getRevisedTravelCharge(int travelCharge) {
        return (int) (0.5 * travelCharge);
    }

    private void collectDiscount(Passenger passenger, int discount) {
        Station station = getStation(passenger.getBoardingStation());
        station.addDiscount(discount);
    }

    private Station getStation(String stationName) {
        return stationRepository.findByName(stationName)
            .orElseThrow(StationNotFoundException::new);
    }
}
