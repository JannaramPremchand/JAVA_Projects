package com.crio.qcontest.commands;

import java.util.List;

// import com.crio.qcontest.entities.Contest;
import com.crio.qcontest.entities.DifficultyLevel;
import com.crio.qcontest.services.ContestService;

public class CreateContestCommand implements ICommand {

    private final ContestService contestService; 

    public CreateContestCommand(ContestService contestService) {
        this.contestService = contestService;
    }

    @Override
    public void invoke(List<String> tokens) {
        String contestName = tokens.get(1);
        String levelStr = tokens.get(2);
        Long userId = Long.parseLong(tokens.get(3));
        Integer numQuestions = Integer.parseInt(tokens.get(4));

        DifficultyLevel level;
        try {
            level = DifficultyLevel.valueOf(levelStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Invalid difficulty level provided.");
            return;
        }

        try {
            var contest = contestService.createContest(contestName, level, userId, numQuestions);
            System.out.println("Contest Id: " + contest.getId());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
