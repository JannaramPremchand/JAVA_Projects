package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.services.MetroCardService;

public class BalanceCommand implements ICommand {
    private final MetroCardService metroCardService;

    public BalanceCommand(MetroCardService metroCardService) {
        this.metroCardService = metroCardService;
    }

    @Override
    public void execute(List<String> tokens) {
        if (tokens == null || tokens.size() < 3) {
            throw new IllegalArgumentException("Invalid input: At least three tokens required.");
        }

        String cardNumber = tokens.get(1);
        int balance = parseBalance(tokens.get(2));

        metroCardService.create(cardNumber, balance);
    }

    private int parseBalance(String balanceToken) {
        try {
            return Integer.parseInt(balanceToken);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid balance amount: " + balanceToken, e);
        }
    }
}
