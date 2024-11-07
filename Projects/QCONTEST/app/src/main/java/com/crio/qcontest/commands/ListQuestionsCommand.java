package com.crio.qcontest.commands;

import java.util.List;

import com.crio.qcontest.entities.DifficultyLevel;
import com.crio.qcontest.entities.Question;
import com.crio.qcontest.services.QuestionService;

public class ListQuestionsCommand implements ICommand{

    private final QuestionService questionService;

    public ListQuestionsCommand(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public void invoke(List<String> tokens) {
        if (tokens.size() < 2) {
            System.out.println("Invalid command");
            return;
        }

        try {
            DifficultyLevel level = DifficultyLevel.valueOf(tokens.get(1));
            List<Question> questions = questionService.listQuestions(level);
            System.out.println(questions);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid difficulty level");
        }
    }
    
}
