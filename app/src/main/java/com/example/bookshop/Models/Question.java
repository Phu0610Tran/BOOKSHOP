package com.example.bookshop.Models;

import java.util.List;

public class Question {
    private String content;
    private int number;
    private List<Answer> answerList;

    public Question(String content, int number, List<Answer> answerList) {
        this.content = content;
        this.number = number;
        this.answerList = answerList;
    }
    public Question(String content,List<Answer> answerList) {
        this.content = content;
        this.answerList = answerList;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }
}
