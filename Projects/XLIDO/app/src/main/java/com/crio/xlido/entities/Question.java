package com.crio.xlido.entities;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private int questionId;    // Unique identifier for the question
    private String text;        // Text of the question
    private int userId;        // ID of the user who asked the question
    private int eventId;       // ID of the event associated with the question
    private int upvoteCount;    // Number of upvotes for the question
    private List<Reply> replies; // List to hold replies to this question

    // Constructor
    public Question(int questionId, String text, int userId, int eventId) {
        this.questionId = questionId;
        this.text = text;
        this.userId = userId;
        this.eventId = eventId;
        this.upvoteCount = 0; // Initial upvote count set to zero
        this.replies = new ArrayList<>(); // Initialize the replies list
    }

    // Getters
    public int getQuestionId() {
        return questionId;
    }

    public String getText() {
        return text;
    }

    public int getUserId() {
        return userId;
    }

    public int getEventId() {
        return eventId;
    }

    public int getUpvoteCount() {
        return upvoteCount;
    }

    // Method to increment the upvote count
    public void upvote() {
        upvoteCount++;
    }

    // Method to add a reply to this question
    public void addReply(Reply reply) {
        replies.add(reply);
    }

    // Method to get all replies
    public List<Reply> getReplies() {
        return replies;
    }

    // Override toString for better output
    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", text='" + text + '\'' +
                ", userId=" + userId +
                ", eventId=" + eventId +
                ", upvoteCount=" + upvoteCount +
                '}';
    }
}
