package com.testingcenter.controller;

import com.testingcenter.controller.exceptions.IncorrectPageException;
import com.testingcenter.controller.exceptions.NoTestsForTeacherException;
import com.testingcenter.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to make business logic with tests
 *
 * @author Matveev Alexander
 */
public class TestController {
    /**
     * Method to find all tests of the teacher
     *
     * @param teacher Teacher who make test
     * @return List of tests created by teacher
     */
    public List<Test> getTeachersTests(Teacher teacher) throws NoTestsForTeacherException {
        List<Test> tests = Repository.getTests();
        List<Test> result = new ArrayList<>();
        for (Test test : tests)
            if (test.getTeacher() == teacher)
                result.add(test);
        if (result.isEmpty()) {
            throw new NoTestsForTeacherException("Tests for teacher - " + teacher.getFirstName() + " " + teacher.getLastName() + " not founded");
        }
        return result;
    }

    /**
     * Method to get one page of teachers tests
     *
     * @param limit       number of records on page
     * @param offset      offset from start
     * @param teacher     teacher whom tests we want to get
     * @param sortingCode option to sort collection. 1- to sort by name. 2- to sort by coefficient of test, 3- to sort by teachers last name
     * @return page of teachers tests
     * @throws IncorrectPageException when method cant form a page
     */
    public List<Test> getTeacherTests(int limit, int offset, Teacher teacher, int sortingCode) throws IncorrectPageException {
        List<Test> teacherTests = getTeachersTests(teacher);
        teacherTests = new TestsSearchController(sortingCode).sortTests(teacherTests).stream().skip(offset).limit(limit).collect(Collectors.toList());
        if (teacherTests.isEmpty())
            throw new IncorrectPageException("No more pages for tests");
        return teacherTests;
    }

    /**
     * Method to get results of the test
     *
     * @param test Test to get results
     * @return String with progress of test
     */
    public String getTestResults(Test test) {
        int allTests = 0;
        int completedTests = 0;
        List<Assignment> assignments = Repository.getAssignments();
        for (Assignment assignment : assignments)
            if (assignment.getTest() == test) {
                allTests++;
                if (assignment.getIsCompleted())
                    completedTests++;
            }
        if (allTests > 0) {
            return completedTests + "/" + allTests + " completed";
        } else {
            return "No assignments for " + test.getName();
        }
    }

    /**
     * Method to get number of questions in test
     *
     * @param test Test to know how many number of questions
     * @return number of questions
     */
    public int getQuestionNumber(Test test) {
        int i = 0;
        List<TestQuestion> questions = Repository.getQuestions();
        for (TestQuestion question : questions)
            if (question.getQuestionTest() == test)
                i++;
        return i;
    }

    /**
     * Method to get maximum points depends on tests questions
     *
     * @param test test we want to know points
     * @return maximum points student cant get from this test
     */
    public int getTestMaxPoints(Test test) {
        int points = 0;
        List<TestQuestion> list = Repository.getQuestions();
        TestQuestionController qController = new TestQuestionController();
        for (TestQuestion question : list)
            if (question.getQuestionTest() == test)
                points += qController.getQuestionMaxPoints(question);
        return points;
    }

    /**
     * Method get all unanswered questions from test for student
     *
     * @param test test to get questions from
     * @return Collection of unanswered questions
     */
    public List<TestQuestion> getTestQuestions(Test test) {
        return Repository.getQuestions().
                stream().filter(p -> p.getQuestionTest() == test).
                collect(Collectors.toList());
    }

    /**
     * Method to get students answers for test
     *
     * @param student student who's answers we wanna retrieve
     * @param test    test answers we want to get
     * @return collection of answers of student for test
     */
    public List<StudentAnswerForTestQuestion> getStudentsAnswersForTest(Student student, Test test) {
        List<StudentAnswerForTestQuestion> answers = new StudentController().getStudentAnswers(student);
        List<StudentAnswerForTestQuestion> result = new ArrayList<>();
        TestQuestionController testQuestionController = new TestQuestionController();
        for (StudentAnswerForTestQuestion answer : answers) {
            TestQuestion question = testQuestionController.getQuestionById(answer.getTestQuestionId());
            if (question.getQuestionTest() == test)
                result.add(answer);
        }
        return result;
    }

    /**
     * Method to create new test result right after student complete test
     *
     * @param student student who complete test
     * @param test    test completed by student
     * @return Test result
     */
    public TestResult createTestResult(Student student, Test test) {
        TestResult result = new TestResult(student, test, getStudentsResultForTest(student, test));
        Repository.getResults().add(result);
        return result;
    }

    private int getStudentsResultForTest(Student student, Test test) {
        int studentPointsForTest = 0;
        List<StudentAnswerForTestQuestion> answerForTestQuestions = getStudentsAnswersForTest(student, test);
        TestQuestionController testQuestionController = new TestQuestionController();
        for (StudentAnswerForTestQuestion studentAnswerForTestQuestion : answerForTestQuestions) {
            int pointsForSingleQuestion = 0;
            int[] array = studentAnswerForTestQuestion.getAnswersId();
            for (int i : array) {
                QuestionAnswer questionAnswer = testQuestionController.getQuestionAnswerById(i);
                pointsForSingleQuestion += questionAnswer.getPoints();
            }
            if (pointsForSingleQuestion > 0)
                studentPointsForTest += pointsForSingleQuestion;
        }
        return studentPointsForTest;
    }

    /**
     * Method to get is student pass test or not
     *
     * @param student student who complete test
     * @param test    test completed by student
     * @return true if student earn enough points, false if student dont earn enough points
     */
    public boolean isTestPassed(Student student, Test test) {
        return getRatio(student, test) >= test.getCoefficientToPass();
    }

    /**
     * Method to get Ratio of student points compare to max points for test
     *
     * @param student student who complete test
     * @param test    test completed by student
     * @return ratio of student points compare to max points for test
     */
    public double getRatio(Student student, Test test) {
        int studentPoints = getStudentsResultForTest(student, test);
        int testMaxPoints = getTestMaxPoints(test);
        return (double) studentPoints / (double) testMaxPoints;
    }

    /**
     * Method to get student answers for single question
     *
     * @param testQuestion question to get answers
     * @param student      student who answers to qustion
     * @return Collection of answers for question
     */
    public List<QuestionAnswer> getStudentAnswersForQuestion(TestQuestion testQuestion, Student student) {
        List<StudentAnswerForTestQuestion> answerForTestQuestions = getStudentsAnswersForTest(student, testQuestion.getQuestionTest());
        List<QuestionAnswer> result = new ArrayList<>();
        TestQuestionController testQuestionController = new TestQuestionController();
        for (StudentAnswerForTestQuestion sAFTQ : answerForTestQuestions) {
            TestQuestion testQuestion1 = testQuestionController.getQuestionById(sAFTQ.getTestQuestionId());
            if (testQuestion1 == testQuestion) {
                int[] answersId = sAFTQ.getAnswersId();
                for (int i : answersId)
                    result.add(testQuestionController.getQuestionAnswerById(i));
            }
        }
        return result;
    }

    /**
     * Method to get points for single student answer for question
     *
     * @param answers answers of student
     * @return points for answering single question
     */
    public int getPointsForStudentAnswerForQuestion(List<QuestionAnswer> answers) {
        int result = 0;
        for (QuestionAnswer questionAnswer : answers)
            result += questionAnswer.getPoints();
        return Math.max(result, 0);
    }


}

