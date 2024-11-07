package com.crio.qcontest.commands;

import java.util.List;

import com.crio.qcontest.entities.Contest;
import com.crio.qcontest.entities.DifficultyLevel;
import com.crio.qcontest.services.ContestService;

public class ListContestsCommand implements ICommand {

    private final ContestService contestService;

    public ListContestsCommand(ContestService contestService) {
        this.contestService = contestService;
    }

    @Override
    public void invoke(List<String> tokens) {
        String level = tokens.get(1);
        DifficultyLevel difficultyLevel = DifficultyLevel.valueOf(level);
        List<Contest> contests = contestService.listContests(difficultyLevel);
        if (!contests.isEmpty()) {
            System.out.println(contests);
        } else {
            System.out.println("No contests available for the given difficulty level.");
        }
    }
    
}
