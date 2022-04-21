package com.example.bookshop.Models;

public class Answer {
    private String content;
    private String isCorrect;

    public Answer(String content, String isCorrect) {
        this.content = content;
        this.isCorrect = isCorrect;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(String isCorrect) {
        this.isCorrect = isCorrect;
    }

    public String isCorrect() {
        return isCorrect;
    }

    public void setCorrect(String correct) {
        isCorrect = correct;
    }
}
