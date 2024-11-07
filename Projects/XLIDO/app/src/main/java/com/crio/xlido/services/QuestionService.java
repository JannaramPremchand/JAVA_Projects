package com.crio.xlido.services;

import com.crio.xlido.entities.Question;

import java.util.List;

public interface QuestionService {
    Question addQuestion(int userId, int eventId, String questionText);
    void deleteQuestion(int questionId, int userId);
    void upvoteQuestion(int questionId, int userId);
    Question findQuestionById(int questionId);
    void replyToQuestion(int questionId, int userId, String replyContent);
    List<Question> listQuestions(int eventId, String sortBy);
}
