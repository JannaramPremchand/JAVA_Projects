package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.entities.Passenger;
import com.geektrust.backend.entities.PassengerType;
import com.geektrust.backend.exceptions.InvalidAmountException;
import com.geektrust.backend.exceptions.InvalidPassengerException;
import com.geektrust.backend.exceptions.InvalidStationNameException;
import com.geektrust.backend.exceptions.MetroCardNotFoundException;
import com.geektrust.backend.exceptions.StationNotFoundException;
import com.geektrust.backend.services.PassengerService;
import com.geektrust.backend.services.StationService;

public class CheckInCommand implements ICommand {
    private final PassengerService passengerService;
    private final StationService stationService;

    public CheckInCommand(PassengerService passengerService, StationService stationService) {
        this.passengerService = passengerService;
        this.stationService = stationService;
    }

    @Override
    public void execute(List<String> tokens) {
        validateInput(tokens);
        
        String cardNumber = tokens.get(1);
        PassengerType passengerType = parsePassengerType(tokens.get(2));
        String stationName = tokens.get(3);

        try {
            Passenger passenger = passengerService.create(cardNumber, passengerType, stationName);
            stationService.create(stationName); // Assuming create only registers the station if it doesn't exist
            passengerService.travel(passenger);
        } catch (MetroCardNotFoundException | InvalidAmountException | StationNotFoundException | 
                 InvalidStationNameException | InvalidPassengerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void validateInput(List<String> tokens) {
        if (tokens == null || tokens.size() < 4) {
            throw new IllegalArgumentException("Invalid input: At least four tokens required.");
        }
    }

    private PassengerType parsePassengerType(String typeToken) {
        try {
            return PassengerType.valueOf(typeToken.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidPassengerException("Invalid passenger type: " + typeToken);
        }
    }
}
