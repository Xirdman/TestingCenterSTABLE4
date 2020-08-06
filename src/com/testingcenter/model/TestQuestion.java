package com.testingcenter.model;

/**
 * Model class for single Question from test
 *
 * @author Matveev Alexander
 */
public class TestQuestion {
    private int id;
    private String questionText;
    private Test questionTest;

    /**
     * Constructor of class
     *
     * @param questionText text of question
     * @param questionTest test of this question
     */
    public TestQuestion(String questionText, Test questionTest, int id) {
        this.questionText = questionText;
        this.questionTest = questionTest;
        this.id = id;
    }

    /**
     * Get test of this question
     *
     * @return test of question
     */
    public Test getQuestionTest() {
        return questionTest;
    }

    /**
     * Method to get text of question
     *
     * @return text of question
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Method to get identificator of test
     *
     * @return identificator of test
     */
    public int getId() {
        return id;
    }
}
