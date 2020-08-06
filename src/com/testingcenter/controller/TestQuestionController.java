package com.testingcenter.controller;

import com.testingcenter.model.QuestionAnswer;
import com.testingcenter.model.Student;
import com.testingcenter.model.StudentAnswerForTestQuestion;
import com.testingcenter.model.TestQuestion;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to work with test question
 *
 * @author Matveev Alexander
 */
public class TestQuestionController {
    /**
     * Method to get maximum points for single question
     *
     * @param question question to get points
     * @return maximum points for question
     */
    public int getQuestionMaxPoints(TestQuestion question) {
        int points = 0;
        List<QuestionAnswer> list = Repository.getQuestionAnswers();
        for (QuestionAnswer questionAnswer : list) {
            if (questionAnswer.getQuestion() == question) {
                int pointsForAnswer = questionAnswer.getPoints();
                if (pointsForAnswer > 0)
                    points += questionAnswer.getPoints();
            }
        }
        return points;
    }

    /**
     * Method returns collection of answers for single question
     *
     * @param testQuestion test question to get answers
     * @return collection of answers for test question
     */
    public List<QuestionAnswer> getQuestionAnswers(TestQuestion testQuestion) {
        List<QuestionAnswer> allAnswers = Repository.getQuestionAnswers();
        List<QuestionAnswer> result = new ArrayList<>();
        for (QuestionAnswer questionAnswer : allAnswers)
            if (questionAnswer.getQuestion() == testQuestion)
                result.add(questionAnswer);
        return result;
    }

    /**
     * @param arrayOfAnswers answer of student entered from keyboard using spaces between answers
     * @param student        student who answer question
     * @param answers        collection of answers for this question
     * @param question       question answered
     */
    public void processStudentAnswer(int[] arrayOfAnswers, Student student, List<QuestionAnswer> answers, TestQuestion question) {
        if (arrayOfAnswers.length == 0) {
            Repository.getStudentAnswers().add(new StudentAnswerForTestQuestion(student,
                    question.getId()));
        }
        int[] arrayOfId = new int[arrayOfAnswers.length];
        for (int i = 0; i < arrayOfAnswers.length; i++)
            arrayOfId[i] = answers.get(arrayOfAnswers[i] - 1).getAnswerId();
        Repository.getStudentAnswers().add(new StudentAnswerForTestQuestion(student, question.getId(), arrayOfId));
    }

    /**
     * Method to get TestQuestion by id
     *
     * @param questionId id of test question
     * @return test question if it exist, or null if it's not founded
     */
    public TestQuestion getQuestionById(int questionId) {
        List<TestQuestion> questions = Repository.getQuestions();
        for (TestQuestion testQuestion : questions)
            if (testQuestion.getId() == questionId)
                return testQuestion;
        return null;
    }

    /**
     * Method to get question answer by id
     *
     * @param questionAnswerId id of question answer
     * @return question answer with inserted id or null if id not founded
     */
    public QuestionAnswer getQuestionAnswerById(int questionAnswerId) {
        List<QuestionAnswer> answers = Repository.getQuestionAnswers();
        for (QuestionAnswer answer : answers)
            if (answer.getAnswerId() == questionAnswerId)
                return answer;
        return null;
    }
}
