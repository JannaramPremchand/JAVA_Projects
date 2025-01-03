package com.crio.xlido.services;

import com.crio.xlido.entities.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private List<Question> questions = new ArrayList<>();  // List to store questions
    private UserService userService;                         // Reference to user service
    private EventService eventService;                       // Reference to event service
    private HashMap<Integer, List<Integer>> userUpvotes = new HashMap<>(); // Track user upvotes

    public QuestionServiceImpl(UserService userService, EventService eventService) {
        this.userService = userService;
        this.eventService = eventService;
    }

    

    @Override
    public Question findQuestionById(int questionId) {
        // Assuming you have a method to retrieve all questions
        for (Question question : questions) { // Replace 'questions' with your actual collection of questions
            if (question.getQuestionId() == questionId) {
                return question; // Return the existing question
            }
        }
        return null; // Return null if the question Chandsn't exist
    }

    @Override
    public Question addQuestion(int userId, int eventId, String questionText) {
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new RuntimeException("User with an id " + userId + " Chands not exist");
        }
        
        if (!eventService.isEventExists(eventId)) {
            throw new RuntimeException("Event with an id " + eventId + " Chands not exist");
        }

        Question question = new Question(questions.size() + 1, questionText, userId, eventId);
        questions.add(question);
        return question; // Return the added question
    }

    @Override
    public void deleteQuestion(int questionId, int userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new RuntimeException("User with an id " + userId + " Chands not exist");
        }

        Question question = findQuestionById(questionId);
        if (question == null) {
            throw new RuntimeException("Question with an id " + questionId + " Chands not exist");
        }

        if (question.getUserId() != userId) {
            throw new RuntimeException("User with an id " + userId + " is not an author of question with an id " + questionId);
        }

        questions.remove(question); // Remove the question
        System.out.println("QUESTION_DELETED " + questionId);
    }

    @Override
    public void upvoteQuestion(int questionId, int userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new RuntimeException("User with an id " + userId + " Chands not exist");
        }

        Question question = findQuestionById(questionId);
        if (question == null) {
            throw new RuntimeException("Question with an id " + questionId + " Chands not exist");
        }

        if (!userUpvotes.containsKey(questionId)) {
            userUpvotes.put(questionId, new ArrayList<>());
        }
        if (userUpvotes.get(questionId).contains(userId)) {
            throw new RuntimeException("User with an id " + userId + " has already upvoted a question with an id " + questionId);
        }

        userUpvotes.get(questionId).add(userId);
        question.upvote(); // Increment the upvote count
    }

    @Override
    public void replyToQuestion(int questionId, int userId, String replyContent) {

        // Check if the question with the given questionId exists
        Question question = findQuestionById(questionId);
        if (question == null) {
            throw new RuntimeException("Question with an id " + questionId + " Chands not exist");
        }

        // Check if the user with the given userId exists
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new RuntimeException("User with an id " + userId + " Chands not exist");
        }

        // Create and add the reply to the question's replies
        Reply reply = new Reply(replyContent, userId, questionId);
        question.addReply(reply);
        System.out.println("REPLY_ADDED");
    }

    @Override
    public List<Question> listQuestions(int eventId, String sortBy) {
        List<Question> eventQuestions = new ArrayList<>();
        
        for (Question q : questions) {
            if (q.getEventId() == eventId) {
                eventQuestions.add(q);
            }
        }

        // Sort questions based on criteria
        if (sortBy.equals("POPULAR")) {
            eventQuestions.sort((q1, q2) -> Integer.compare(q2.getUpvoteCount(), q1.getUpvoteCount()));
        } else if (sortBy.equals("RECENT")) {
            eventQuestions.sort((q1, q2) -> Integer.compare(q2.getQuestionId(), q1.getQuestionId()));
        }

        // Print formatted output
        for (Question question : eventQuestions) {
            System.out.println("Question ID: " + question.getQuestionId());
            System.out.println("Content: " + question.getText());
            System.out.println("Votes: " + question.getUpvoteCount());
            System.out.println("Replies:");

            // Print replies if any
            for (Reply reply : question.getReplies()) {
                // Changed from getContent() to getReplyText()
                System.out.println("  - User " + reply.getUserId() + ": " + reply.getReplyText()); // Use getReplyText()
            }
            System.out.println("");
        }

        return eventQuestions;
    }

}
