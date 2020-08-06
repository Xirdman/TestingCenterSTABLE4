package com.testingcenter.controller;

import com.testingcenter.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to make business logic with Student user
 *
 * @author Matveev Alexander
 */
public class StudentContoller {
    /**
     * Method to get all tests assigned to student
     *
     * @param student Student to find a tests
     * @return List of student uncomplited tests
     */
    public List<Test> getStudentTests(Student student) {
        List<Test> studentTests = new ArrayList<>();
        List<Assignment> assignments = Repository.getAssignments();
        for (Assignment assignment : assignments)
            if (assignment.getStudent() == student && !assignment.getIsCompleted())
                studentTests.add(assignment.getTest());
        return studentTests;
    }

    /**
     * Method to set single student rating
     *
     * @param student student to set rating
     */
    public void setRating(Student student) {
        int allPoints = 0;
        int studentPoints = 0;
        List<TestResult> results = Repository.getResults();
        for (TestResult testResult : results)
            if (testResult.getStudent() == student) {
                studentPoints += testResult.getPoints();
                allPoints += new TestController().getTestMaxPoints(testResult.getTest());
            }
        if (allPoints != 0)
            student.setRating((double) studentPoints / (double) allPoints * 10);
        else student.setRating(0);
    }

    /**
     * Method to get Assignment to test for student
     *
     * @param student student who was assigned to complete test
     * @param test    test assigned to complete
     * @return Assignment for student to complete test, or null if assignment not founded
     */
    public Assignment getAssignmentForStudentsTest(Student student, Test test) {
        List<Assignment> assignments = Repository.getAssignments();
        for (Assignment assignment : assignments)
            if (assignment.getStudent() == student && assignment.getTest() == test)
                return assignment;
        return null;
    }

    /**
     * Method to get all answers by student
     *
     * @param student student who's answers we want to get
     * @return collection of all answers of student
     */
    public List<StudentAnswerForTestQuestion> getStudentAnswers(Student student) {
        return Repository.getStudentAnswers().
                stream().filter(p -> p.getStudent() == student).
                collect(Collectors.toList());
    }

}
