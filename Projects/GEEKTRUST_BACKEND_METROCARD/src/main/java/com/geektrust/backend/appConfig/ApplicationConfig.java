package com.geektrust.backend.appConfig;

import com.geektrust.backend.commands.BalanceCommand;
import com.geektrust.backend.commands.CheckInCommand;
import com.geektrust.backend.commands.CommandInvoker;
import com.geektrust.backend.commands.PrintSummaryCommand;
import com.geektrust.backend.repositories.MetroCardRepository;
import com.geektrust.backend.repositories.MetroCardRepositoryImpl;
import com.geektrust.backend.repositories.PassengerRepository;
import com.geektrust.backend.repositories.PassengerRepositoryImpl;
import com.geektrust.backend.repositories.StationRepository;
import com.geektrust.backend.repositories.StationRepositoryImpl;
import com.geektrust.backend.services.MetroCardService;
import com.geektrust.backend.services.MetroCardServiceImpl;
import com.geektrust.backend.services.PassengerService;
import com.geektrust.backend.services.PassengerServiceImpl;
import com.geektrust.backend.services.StationService;
import com.geektrust.backend.services.StationServiceImpl;

public class ApplicationConfig {
    private final CommandInvoker commandInvoker;

    public ApplicationConfig() {
        MetroCardRepository metroCardRepository = new MetroCardRepositoryImpl();
        PassengerRepository passengerRepository = new PassengerRepositoryImpl();
        StationRepository stationRepository = new StationRepositoryImpl();

        StationService stationService = new StationServiceImpl(stationRepository);
        MetroCardService metroCardService = new MetroCardServiceImpl(stationService, metroCardRepository);
        PassengerService passengerService = new PassengerServiceImpl(stationService, metroCardService, metroCardRepository, passengerRepository);

        this.commandInvoker = new CommandInvoker();
        registerCommands(metroCardService, passengerService, stationService);
    }

    private void registerCommands(MetroCardService metroCardService, PassengerService passengerService, StationService stationService) {
        commandInvoker.register("BALANCE", new BalanceCommand(metroCardService));
        commandInvoker.register("CHECK_IN", new CheckInCommand(passengerService, stationService));
        commandInvoker.register("PRINT_SUMMARY", new PrintSummaryCommand(stationService));
    }

    public CommandInvoker getCommandInvoker() {
        return commandInvoker;
    }
}
