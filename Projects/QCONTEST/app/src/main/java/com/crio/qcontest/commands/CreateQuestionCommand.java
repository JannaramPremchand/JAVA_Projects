package com.crio.qcontest.commands;

import java.util.List;

import com.crio.qcontest.entities.DifficultyLevel;
import com.crio.qcontest.entities.Question;
import com.crio.qcontest.services.QuestionService;

public class CreateQuestionCommand implements ICommand{
    private final QuestionService questionService;

    public CreateQuestionCommand(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public void invoke(List<String> tokens) {
        if (tokens.size() < 4) {
            System.out.println("Invalid command format.");
            return;
        }

        String text = tokens.get(1);
        DifficultyLevel level;
        try {
            level = DifficultyLevel.valueOf(tokens.get(2).toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid difficulty level.");
            return;
        }
        Integer score;
        try {
            score = Integer.parseInt(tokens.get(3));
        } catch (NumberFormatException e) {
            System.out.println("Invalid score.");
            return;
        }

        Question newQuestion = questionService.createQuestion(text, level, score);
        System.out.println("Question Id: " + newQuestion.getId());
    }

}
