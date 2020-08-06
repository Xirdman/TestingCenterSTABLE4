package com.testingcenter.model;


/**
 * Class for student answer when he complete test
 *
 * @author Matveev Alexander
 */
public class StudentAnswerForTestQuestion {
    private Student student;
    private int testQuestionId;
    private int[] answerId;
    private boolean isSkipped;

    /**
     * Method to get student who give this answer
     *
     * @return student who give this answer
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Method to get Test question ID
     *
     * @return Test question ID
     */
    public int getTestQuestionId() {
        return testQuestionId;
    }

    /**
     * Method to get question answer ID
     *
     * @return question answer ID
     */
    public int[] getAnswersId() {
        return answerId;
    }

    /**
     * Method to get was question skipped
     *
     * @return true if question was skipped, false if question was answered
     */
    public boolean isSkipped() {
        return isSkipped;
    }

    /**
     * Constructor of class
     *
     * @param student        student who answer for question
     * @param testQuestionId identificator of question answered by student
     * @param answerId       identificator of answer chosen by student
     */
    public StudentAnswerForTestQuestion(Student student, int testQuestionId, int[] answerId) {
        this.student = student;
        this.testQuestionId = testQuestionId;
        this.answerId = answerId;
        isSkipped = false;
    }

    /**
     * Constructor of class if question was skipped by student
     *
     * @param student        student who answer for question
     * @param testQuestionId identificator of question answered by student
     */
    public StudentAnswerForTestQuestion(Student student, int testQuestionId) {
        this.student = student;
        this.testQuestionId = testQuestionId;
        isSkipped = true;
    }
}
