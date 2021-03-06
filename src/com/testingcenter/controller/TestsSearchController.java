package com.testingcenter.controller;

import com.testingcenter.controller.exceptions.IncorrectPageException;
import com.testingcenter.controller.exceptions.NoElementsFoundException;
import com.testingcenter.model.Test;
import com.testingcenter.model.sortingoptions.TestsSortingOption;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to Search in tests and get result
 */
public class TestsSearchController {

    private TestsSortingOption sortingOption;

    /**
     * Constructor of class
     *
     * @param sortingOption option to sort resulting collection of tests
     */
    public TestsSearchController(TestsSortingOption sortingOption) {
        this.sortingOption = sortingOption;

    }

    /**
     * Method to sort test
     *
     * @param testsToSort list of tests to sort
     * @return sorted list of tests
     */
    public List<Test> sortTests(List<Test> testsToSort) {
        switch (sortingOption) {
            case SORT_TESTS_BY_COEFFICIENT_TO_PASS:
                return testsToSort.stream()
                        .sorted((a, b) -> Double.compare(a.getCoefficientToPass(), b.getCoefficientToPass()))
                        .collect(Collectors.toList());
            case SORT_TESTS_BY_NAME:
                return testsToSort.stream()
                        .sorted((a, b) -> a.getName().compareTo(b.getName()))
                        .collect(Collectors.toList());
            case SORT_TESTS_BY_TEACHER_LAST_NAME:
                return testsToSort.stream()
                        .sorted((a, b) -> a.getTeacher().getLastName().compareTo(b.getTeacher().getLastName()))
                        .collect(Collectors.toList());
            default:
                return testsToSort;
        }
    }

    private List<Test> searchTestsByName(String textInName) throws NoElementsFoundException {
        List<Test> tests = Repository.getTests();
        tests = tests.stream().filter(e -> e.getName().contains(textInName)).collect(Collectors.toList());
        if (tests.isEmpty())
            throw new NoElementsFoundException("No tests found with name - " + textInName);
        return tests;
    }

    /**
     * Method to search tests by name
     *
     * @param limit           number of records on page
     * @param offset          offset from start
     * @param searchParameter name of test
     * @return page of searched result
     * @throws IncorrectPageException   thrown when cant form a page
     * @throws NoElementsFoundException thrown when there is no tests with such parameter
     */
    public List<Test> searchTestsByName(int limit, int offset, String searchParameter) throws IncorrectPageException, NoElementsFoundException {
        List<Test> searchedTests = searchTestsByName(searchParameter);
        searchedTests = sortTests(searchedTests).stream().skip(offset).limit(limit).collect(Collectors.toList());
        if (searchedTests.isEmpty())
            throw new IncorrectPageException("No tests at this page");
        return searchedTests;
    }

    private List<Test> searchTestsByCoefficient(double coefficient) throws NoElementsFoundException {
        List<Test> tests = Repository.getTests();
        tests = tests.stream()
                .filter(e -> (Math.abs(coefficient - e.getCoefficientToPass()) < 0.001))
                .collect(Collectors.toList());
        if (tests.isEmpty())
            throw new NoElementsFoundException("No tests found with coefficient - " + coefficient);
        return tests;
    }

    /**
     * Method to search tests by coefficient to pass
     *
     * @param limit           number of records on page
     * @param offset          offset from start
     * @param searchParameter coefficient to pass test
     * @return page of searched result
     * @throws IncorrectPageException   thrown when cant form a page
     * @throws NoElementsFoundException thrown when there is no tests with such parameter
     */
    public List<Test> searchTestByCoefficient(int limit, int offset, double searchParameter) {
        List<Test> tests = searchTestsByCoefficient(searchParameter);
        tests = sortTests(tests).stream().skip(offset).limit(limit).collect(Collectors.toList());
        if (tests.isEmpty())
            throw new IncorrectPageException("No tests at this page");
        return tests;
    }

    private List<Test> searchTestsByTeacherLastName(String teacherLastName) {
        List<Test> tests = Repository.getTests();
        tests = tests.stream().filter(a -> a.getTeacher().getLastName().contains(teacherLastName)).collect(Collectors.toList());
        if (tests.isEmpty())
            throw new NoElementsFoundException("No tests with teacher last name " + teacherLastName + " not found");
        return tests;
    }

    /**
     * Method to search tests by teacher last name
     *
     * @param limit           number of records on page
     * @param offset          offset from start
     * @param searchParameter last name of tests teacher
     * @return page of searched result
     * @throws IncorrectPageException   thrown when cant form a page
     * @throws NoElementsFoundException thrown when there is no tests with such parameter
     */
    public List<Test> searchTestsByTeacherLastName(int limit, int offset, String searchParameter) {
        List<Test> tests = searchTestsByTeacherLastName(searchParameter);
        tests = sortTests(tests).stream().skip(offset).limit(limit).collect(Collectors.toList());
        if (tests.isEmpty())
            throw new IncorrectPageException("No tests at this page");
        return tests;
    }
}
