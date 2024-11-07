package com.geektrust.backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.geektrust.backend.appConfig.ApplicationConfig;
import com.geektrust.backend.commands.CommandInvoker;
import com.geektrust.backend.exceptions.NoSuchCommandException;

public class App {
    public static void main(String[] args) {
        final int INPUT_FILE_INDEX = 0;

        if (args.length < 1) {
            System.err.println("Please provide the input file path as an argument.");
            return;
        }

        String inputFile = args[INPUT_FILE_INDEX];
        List<String> commandLineArgs = new LinkedList<>(Arrays.asList(args));
        commandLineArgs.remove(INPUT_FILE_INDEX);

        ApplicationConfig applicationConfig = new ApplicationConfig();
        CommandInvoker commandInvoker = applicationConfig.getCommandInvoker();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processLine(line, commandLineArgs, commandInvoker);
            }
        } catch (IOException e) {
            System.err.println("Error reading the input file: " + e.getMessage());
        }
    }

    private static void processLine(String line, List<String> commandLineArgs, CommandInvoker commandInvoker) {
        if (line.isEmpty()) {
            System.err.println("Warning: Empty command line detected.");
            return;
        }

        List<String> tokens = Arrays.asList(line.split(" "));
        String commandName = tokens.get(0);
        try {
            commandInvoker.executeCommand(commandName, tokens);
        } catch (NoSuchCommandException e) {
            System.err.println("Error: No such command - " + commandName);
        } catch (Exception e) {
            System.err.println("An error occurred while executing command: " + e.getMessage());
        }
    }
}
