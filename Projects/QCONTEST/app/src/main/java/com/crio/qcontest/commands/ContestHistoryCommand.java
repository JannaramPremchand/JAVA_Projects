package com.crio.qcontest.commands;

import java.util.List;

import com.crio.qcontest.entities.Contestant;
import com.crio.qcontest.entities.Question;
import com.crio.qcontest.services.ContestService;

public class ContestHistoryCommand implements ICommand {

    private final ContestService contestService;

    public ContestHistoryCommand(ContestService contestService) {
        this.contestService = contestService;
    }

    @Override
    public void invoke(List<String> tokens) {
        Long contestId = Long.parseLong(tokens.get(1));
        List<Contestant> contestants = contestService.contestHistory(contestId);
        for (Contestant contestant : contestants) {
            StringBuilder questionIds = new StringBuilder("[");
            List<Question> solvedQuestions = contestant.getSolvedQuestions();
            
            for (int i = 0; i < solvedQuestions.size(); i++) {
                questionIds.append(solvedQuestions.get(i).getId());
                if (i < solvedQuestions.size() - 1) {
                    questionIds.append(", ");
                }
            }
            questionIds.append("]");
            
            System.out.println(contestant.getUser().getName() + " : " + contestant.getTotalScore() + " " + questionIds.toString());
        }
    }
    
}
