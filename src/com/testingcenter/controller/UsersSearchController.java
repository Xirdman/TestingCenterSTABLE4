package com.testingcenter.controller;

import com.testingcenter.controller.exceptions.IncorrectPageException;
import com.testingcenter.controller.exceptions.NoElementsFoundException;
import com.testingcenter.model.User;
import com.testingcenter.model.sortingoptions.UsersSortingOption;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to search in students
 */
public class UsersSearchController {
    private UsersSortingOption sortOption;

    /**
     * Constructor of class
     * @param option option to sort resulting collection of users
     */
    public UsersSearchController(UsersSortingOption option) {
        sortOption = option;
    }

    private List<User> sortUsers(List<User> usersToSort) {
        switch (sortOption) {
            case SORT_USERS_BY_LAST_NAME:
                return usersToSort.stream()
                        .sorted((a, b) -> a.getLastName().compareTo(b.getLastName()))
                        .collect(Collectors.toList());
            case SORT_USERS_BY_FIRST_NAME:
                return usersToSort.stream()
                        .sorted((a, b) -> a.getFirstName().compareTo(b.getFirstName()))
                        .collect(Collectors.toList());
            case SORT_USERS_BY_MIDDLE_NAME:
                return usersToSort.stream()
                        .sorted((a, b) -> a.getMiddleName().compareTo(b.getMiddleName()))
                        .collect(Collectors.toList());
            case SORT_USERS_BY_DATE_OF_BIRTH:
                return usersToSort.stream()
                        .sorted((a, b) -> a.getDateOfBirth().compareTo(b.getDateOfBirth()))
                        .collect(Collectors.toList());
            default:
                return usersToSort;
        }
    }

    private List<User> searchByFirstName(String firstName) throws NoElementsFoundException {
        List<User> users = Repository.getUsers();
        users = users.stream().filter(a -> a.getFirstName().contains(firstName)).collect(Collectors.toList());
        if (users.isEmpty())
            throw new NoElementsFoundException("No users with first name " + firstName);
        return users;
    }

    private List<User> searchByLastName(String lastName) throws NoElementsFoundException {
        List<User> users = Repository.getUsers();
        users = users.stream().filter(a -> a.getLastName().contains(lastName)).collect(Collectors.toList());
        if (users.isEmpty())
            throw new NoElementsFoundException("No users with last name " + lastName);
        return users;
    }

    private List<User> searchByMiddleName(String middleName) throws NoElementsFoundException {
        List<User> users = Repository.getUsers();
        users = users.stream().filter(a -> a.getMiddleName().contains(middleName)).collect(Collectors.toList());
        if (users.isEmpty())
            throw new NoElementsFoundException("No users with middle name " + middleName);
        return users;
    }

    private List<User> searchByDayOfBirth(int dayOfBirth) throws NoElementsFoundException {
        List<User> users = Repository.getUsers();
        users = users.stream()
                .filter(a -> a.getDateOfBirth().get(Calendar.DAY_OF_MONTH) == dayOfBirth)
                .collect(Collectors.toList());
        if (users.isEmpty())
            throw new NoElementsFoundException("No users with day of birth " + dayOfBirth);
        return users;
    }

    private List<User> searchByMonthOfBirth(int monthOfBirth) throws NoElementsFoundException {
        List<User> users = Repository.getUsers();
        users = users.stream()
                .filter(a -> a.getDateOfBirth().get(Calendar.MONTH) == monthOfBirth)
                .collect(Collectors.toList());
        if (users.isEmpty())
            throw new NoElementsFoundException("No users with month of birth " + monthOfBirth);
        return users;
    }

    private List<User> searchByYearOfBirth(int yearOfBirth) throws NoElementsFoundException {
        List<User> users = Repository.getUsers();
        users = users.stream()
                .filter(a -> a.getDateOfBirth().get(Calendar.YEAR) == yearOfBirth)
                .collect(Collectors.toList());
        if (users.isEmpty())
            throw new NoElementsFoundException("No users with year of birth " + yearOfBirth);
        return users;
    }

    /**
     * Method to search users by first name
     *
     * @param limit           number of records on page
     * @param offset          offset from start
     * @param searchParameter first name of user to search
     * @return page of searched users
     * @throws IncorrectPageException   thrown when cant form a page
     * @throws NoElementsFoundException thrown when there is no tests with such parameter
     */
    public List<User> searchByFirstName(int limit, int offset, String searchParameter) throws IncorrectPageException, NoElementsFoundException {
        List<User> searchedUsers = searchByFirstName(searchParameter);
        searchedUsers = sortUsers(searchedUsers).stream().skip(offset).limit(limit).collect(Collectors.toList());
        if (searchedUsers.isEmpty())
            throw new IncorrectPageException("No elements to display on this page");
        return searchedUsers;
    }

    /**
     * Method to search users by last name
     *
     * @param limit           number of records on page
     * @param offset          offset from start
     * @param searchParameter last name of user to search
     * @return page of searched users
     * @throws IncorrectPageException   thrown when cant form a page
     * @throws NoElementsFoundException thrown when there is no tests with such parameter
     */
    public List<User> searchByLastName(int limit, int offset, String searchParameter) throws IncorrectPageException, NoElementsFoundException {
        List<User> searchedUsers = searchByLastName(searchParameter);
        searchedUsers = sortUsers(searchedUsers).stream().skip(offset).limit(limit).collect(Collectors.toList());
        if (searchedUsers.isEmpty())
            throw new IncorrectPageException("No elements to display on this page");
        return searchedUsers;
    }

    /**
     * Method to search users by middle name
     *
     * @param limit           number of records on page
     * @param offset          offset from start
     * @param searchParameter middle name of user to search
     * @return page of searched users
     * @throws IncorrectPageException   thrown when cant form a page
     * @throws NoElementsFoundException thrown when there is no tests with such parameter
     */
    public List<User> searchByMiddleName(int limit, int offset, String searchParameter) throws IncorrectPageException, NoElementsFoundException {
        List<User> searchedUsers = searchByMiddleName(searchParameter);
        searchedUsers = sortUsers(searchedUsers).stream().skip(offset).limit(limit).collect(Collectors.toList());
        if (searchedUsers.isEmpty())
            throw new IncorrectPageException("No elements to display on this page");
        return searchedUsers;
    }

    /**
     * Method to search users by day of birth
     *
     * @param limit           number of records on page
     * @param offset          offset from start
     * @param searchParameter day of birth of user to search
     * @return page of searched users
     * @throws IncorrectPageException   thrown when cant form a page
     * @throws NoElementsFoundException thrown when there is no tests with such parameter
     */
    public List<User> searchByDayOfBirth(int limit, int offset, int searchParameter) throws IncorrectPageException, NoElementsFoundException {
        List<User> searchedUsers = searchByDayOfBirth(searchParameter);
        searchedUsers = sortUsers(searchedUsers).stream().skip(offset).limit(limit).collect(Collectors.toList());
        if (searchedUsers.isEmpty())
            throw new IncorrectPageException("No elements to display on this page");
        return searchedUsers;
    }

    /**
     * Method to search users by month of birth
     *
     * @param limit           number of records on page
     * @param offset          offset from start
     * @param searchParameter month of birth of user to search
     * @return page of searched users
     * @throws IncorrectPageException   thrown when cant form a page
     * @throws NoElementsFoundException thrown when there is no tests with such parameter
     */
    public List<User> searchByMonthOfBirth(int limit, int offset, int searchParameter) throws IncorrectPageException, NoElementsFoundException {
        List<User> searchedUsers = searchByMonthOfBirth(searchParameter);
        searchedUsers = sortUsers(searchedUsers).stream().skip(offset).limit(limit).collect(Collectors.toList());
        if (searchedUsers.isEmpty())
            throw new IncorrectPageException("No elements to display on this page");
        return searchedUsers;
    }

    /**
     * Method to search users by year of birth
     *
     * @param limit           number of records on page
     * @param offset          offset from start
     * @param searchParameter year of birth of user to search
     * @return page of searched users
     * @throws IncorrectPageException   thrown when cant form a page
     * @throws NoElementsFoundException thrown when there is no tests with such parameter
     */
    public List<User> searchByYearOfBirth(int limit, int offset, int searchParameter) throws IncorrectPageException, NoElementsFoundException {
        List<User> searchedUsers = searchByYearOfBirth(searchParameter);
        searchedUsers = sortUsers(searchedUsers).stream().skip(offset).limit(limit).collect(Collectors.toList());
        if (searchedUsers.isEmpty())
            throw new IncorrectPageException("No elements to display on this page");
        return searchedUsers;
    }
}

