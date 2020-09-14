package com.testingcenter.model.sortingoptions;

/**
 * Enum for possible options to sort collections of users
 */
public enum UsersSortingOption {
    NO_SORTING("No sorting"),
    SORT_USERS_BY_FIRST_NAME("Sort users by first name"),
    SORT_USERS_BY_LAST_NAME("Sort users by last name"),
    SORT_USERS_BY_MIDDLE_NAME("Sort users by middle name"),
    SORT_USERS_BY_DATE_OF_BIRTH("Sort users by date of birth");


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
    UsersSortingOption(String text) {
        this.text = text;
    }
}
