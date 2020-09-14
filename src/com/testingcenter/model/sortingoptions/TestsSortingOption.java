package com.testingcenter.model.sortingoptions;

/**
 * Enum for possible options to sort collections of tests
 */
public enum TestsSortingOption {
    NO_SORTING("No sorting"),
    SORT_TESTS_BY_NAME("Sort tests by name"),
    SORT_TESTS_BY_COEFFICIENT_TO_PASS("Sort tests by coefficient to pass"),
    SORT_TESTS_BY_TEACHER_LAST_NAME("Sort tests by teacher last name");


    private String text;

    /**
     * Method to get text of type of sorting
     *
     * @return text of type of sorting
     */
    public String getText() {
        return text;
    }

    /**
     * Constructor of class
     *
     * @param text text of sorting
     */
    TestsSortingOption(String text) {
        this.text = text;
    }
}
