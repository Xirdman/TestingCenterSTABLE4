package com.testingcenter.controller;

import com.testingcenter.controller.exceptions.AssignmentNotFoundException;
import com.testingcenter.controller.exceptions.IncorrectInputException;
import com.testingcenter.controller.exceptions.IncorrectPageException;
import com.testingcenter.controller.exceptions.StudentTestNotFoundException;
import com.testingcenter.model.*;
import com.testingcenter.model.sortingoptions.TestsSortingOption;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to make business logic with Student user
 *
 * @author Matveev Alexander
 */
public class StudentController {
    /**
     * Method to get all tests assigned to student
     *
     * @param student Student to find a tests
     * @return List of student uncompleted tests
     */
    public List<Test> getStudentTests(Student student) throws StudentTestNotFoundException {
        List<Test> studentTests = new ArrayList<>();
        List<Assignment> assignments = Repository.getAssignments();
        for (Assignment assignment : assignments)
            if (assignment.getStudent() == student && !assignment.getIsCompleted())
                studentTests.add(assignment.getTest());
        if (studentTests.isEmpty())
            throw new StudentTestNotFoundException("No tests for student " + student.getFirstName() + " " + student.getLastName());
        return studentTests;
    }

    /**
     * Method to get one page of tests assigned to student
     *
     * @param limit         number of records on page
     * @param offset        offset from start
     * @param student       student whom tests we get
     * @param sortingOption option to sort collection. 1- to sort by name. 2- to sort by coefficient of test, 3- to sort by teachers name
     * @return page of tests assigned to student
     * @throws IncorrectPageException when method cant form a page
     */
    public List<Test> getStudentTests(int limit, int offset, Student student, TestsSortingOption sortingOption)throws IncorrectPageException {
        List<Test> tests = getStudentTests(student);
        tests = new TestsSearchController(sortingOption).sortTests(tests).stream().skip(offset).limit(limit).collect(Collectors.toList());
        if (tests.isEmpty())
            throw new IncorrectPageException("No such page");
        return tests;
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
        throw new AssignmentNotFoundException("No assignment for " + student + " to test " + test);
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

    /**
     * Method to check is input from keyboard is correct and can be parsed to integer, and does it more than zero and less than target value
     *
     * @param inputfromKeyboard string to check
     * @param value             maximum value of int, not included
     * @return parsed integer from keyboard
     * @throws IncorrectInputException exception if input is invalid
     */
    public int checkIntegerInputFromZeroToValue(String inputfromKeyboard, int value) throws IncorrectInputException {
        int checkedInt;
        try {
            checkedInt = Integer.parseInt(inputfromKeyboard);
        } catch (NumberFormatException exception) {
            throw new IncorrectInputException("Input is not an integer");
        }
        if (checkedInt < 0)
            throw new IncorrectInputException("Input must be positive");
        else if (checkedInt >= value)
            throw new IncorrectInputException("Input must be less than " + value);
        return checkedInt;
    }

}

