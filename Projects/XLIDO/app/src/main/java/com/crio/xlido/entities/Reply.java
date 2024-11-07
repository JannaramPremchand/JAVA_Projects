package com.crio.xlido.entities;

public class Reply {
    private String replyText;  // Text of the reply
    private int userId;        // ID of the user who made the reply
    private int questionId;    // ID of the question being replied to
    // private String content;
    // Constructor
    public Reply(String replyText, int userId, int questionId) {
        this.replyText = replyText;
        this.userId = userId;
        this.questionId = questionId;
    }

    // Getters
    public String getReplyText() {
        return replyText;
    }

    public int getUserId() {
        return userId;
    }

    public int getQuestionId() {
        return questionId;
    }

    // public Reply(String content) {
    //     this.content = content;
    // }

    // Getter for content
    // public String getContent() {
    //     return content;
    // }
}
