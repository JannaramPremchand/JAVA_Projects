package com.example.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.example.demo.commands.CommandRegistry;

public class App {

    public static void main(String[] args) {
        if (args.length != 1) {
            throw new RuntimeException("Please provide the input file as a command-line argument.");
        }
        List<String> commandLineArgs = new LinkedList<>(Arrays.asList(args));
        run(commandLineArgs);
    }

    public static void run(List<String> commandLineArgs) {
        Configuration conf = Configuration.getInstance();
        CommandRegistry commandRegistry = conf.getCommandRegistry();
        
        String inputFile = commandLineArgs.get(0).split("=")[1];

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                commandRegistry.invokeCommand(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
