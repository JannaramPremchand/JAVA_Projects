package com.crio.qcontest.commands;

import java.util.List;

import com.crio.qcontest.services.ContestService;

public class RunContestCommand implements ICommand {

    private final ContestService contestService;

    public RunContestCommand(ContestService contestService) {
        this.contestService = contestService;
    }

    @Override
    public void invoke(List<String> tokens) {
        if (tokens.size() < 3) {
            throw new RuntimeException("Insufficient arguments provided to run the contest.");
        }
        Long contestId = Long.parseLong(tokens.get(1));
        Long userId = Long.parseLong(tokens.get(2));
        contestService.runContest(contestId, userId);
    }
    
}