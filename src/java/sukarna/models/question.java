/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sukarna.models;

/**
 *
 * @author Tanveer
 */
public class question {
    
    private int questionId;
    private int marks;
    private String questionType;
    private String questionText;
    private String annswer;
    private String optionOne;
    private String optionTwo;
    private String optionThree;
    private String optionFour;
    
    
    
    public question() {
        this.questionId = 0;
        this.marks=0;
        this.questionType = "NULL";
        this.questionText = "NULL";
        this.annswer = "NULL";
        this.optionOne = "NULL";
        this.optionTwo = "NULL";
        this.optionThree = "NULL";
        this.optionFour = "NULL";
    }
    public question(int questionId,int marks, String questionType, String questionText, String annswer, String optionOne, String optionTwo, String optionThree, String optionFour) {
        this.questionId = questionId;
        this.questionType = questionType;
        this.questionText = questionText;
        this.annswer = annswer;
        this.optionOne = optionOne;
        this.optionTwo = optionTwo;
        this.optionThree = optionThree;
        this.optionFour = optionFour;
        this.marks=marks;
    }
    
    public question(int questionId,int marks, String questionType, String questionText, String annswer) {
        this.questionId = questionId;
        this.questionType = questionType;
        this.questionText = questionText;
        this.annswer = annswer;
        this.optionOne = "NULL";
        this.optionTwo = "NULL";
        this.optionThree = "NULL";
        this.optionFour = "NULL";
        this.marks=marks;

    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
    
    public String getOptionFour() {
        return optionFour;
    }

    public void setOptionFour(String optionFour) {
        this.optionFour = optionFour;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getAnnswer() {
        return annswer;
    }

    public void setAnnswer(String annswer) {
        this.annswer = annswer;
    }

    public String getOptionOne() {
        return optionOne;
    }

    public void setOptionOne(String optionOne) {
        this.optionOne = optionOne;
    }

    public String getOptionTwo() {
        return optionTwo;
    }

    public void setOptionTwo(String optionTwo) {
        this.optionTwo = optionTwo;
    }

    public String getOptionThree() {
        return optionThree;
    }

    public void setOptionThree(String optionThree) {
        this.optionThree = optionThree;
    }
    
}
