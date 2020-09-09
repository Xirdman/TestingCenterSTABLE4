package com.testingcenter.view.searchingmenus;

import com.testingcenter.controller.TestsSearchController;
import com.testingcenter.controller.exceptions.IncorrectInputException;
import com.testingcenter.controller.exceptions.IncorrectPageException;
import com.testingcenter.controller.exceptions.NoElementsFoundException;
import com.testingcenter.model.Test;
import com.testingcenter.view.UserMenu;

import java.util.List;
import java.util.Scanner;

/**
 * Class to print menu to search tests
 */
public class TestsSearchMenu extends UserMenu {
    /**
     * Method to print menu to search tests
     */
    public static void printSearchMenu() {
        System.out.print("Choose option how to search tests:\n");
        System.out.print("1 - Search by test name\n");
        System.out.print("2 - Search by coefficient to pass test \n");
        System.out.print("3 - Search by teacher last name \n");
        System.out.print("4 - Back to previous menu\n");
        System.out.print("5 - Log out\n");
        System.out.print("6 - Exit\n");
        int i = 0;
        try {
            i = getIntFromKeyboard();
            System.out.print("\n");
        } catch (IncorrectInputException e) {
            System.out.print("Error: \n");
            System.out.println(e.getMessage());
        }
        switch (i) {
            case 1:
                searchTestsWithName();
                break;
            case 2:
                searchTestsWithCoefficient();
                break;
            case 3:
                searchTestsWithTeacherLastName();
                break;
            case 4:
                break;
            case 5:
                logOut();
                break;
            case 6:
                exit();
                break;
            default:
                System.out.print("Waiting to choose an option from 1 to 6 \n");
                printSearchMenu();
                break;
        }

    }


    private static void searchTestsWithName() {
        System.out.println("Enter test name to search");
        String searchedFirstName = getStringFromKeyboard();
        int optionCode = getTestsSortingOption();
        try {
            printSearchedTestsByTestName(PAGE_SIZE, 0, searchedFirstName, optionCode);
        } catch (NoElementsFoundException e) {
            System.out.print("Error: \n");
            System.out.println(e.getMessage());
        }
    }

    private static void printSearchedTestsByTestName(int limit, int offset, String testName, int usersSortingOption) throws IncorrectPageException {
        List<Test> tests = new TestsSearchController(usersSortingOption).searchTestsByName(limit, offset, testName);
        System.out.println("Tests with name " + testName + " found:");
        tests.forEach(a -> System.out.println(a.getName() + " has coefficient to pass " + a.getCoefficientToPass() +
                " from teacher " + a.getTeacher().getLastName() + " " + a.getTeacher().getFirstName() + " " + a.getTeacher().getMiddleName()));
        System.out.println("page - " + ((offset / limit) + 1) + "\n");
        PagingOptions pagingOption;
        if (offset == 0)
            pagingOption = getPagingOptionWithoutPrevPageOption();
        else pagingOption = getPagingOption();
        switch (pagingOption) {
            case PrevPage:
                try {
                    printSearchedTestsByTestName(limit, offset - limit, testName, usersSortingOption);
                } catch (IncorrectPageException e) {
                    System.out.println("Cant back to previous page\n");
                    printSearchedTestsByTestName(limit, offset, testName, usersSortingOption);
                }
                break;
            case NextPage:
                try {
                    printSearchedTestsByTestName(limit, offset + limit, testName, usersSortingOption);
                } catch (IncorrectPageException e) {
                    System.out.println("Cant go to next page\n");
                    printSearchedTestsByTestName(limit, offset, testName, usersSortingOption);
                }
                break;
            case LogOut:
                logOut();
                break;
            case Back:
                break;
            case Exit:
                exit();
                break;
        }
    }

    private static double getDoubleToSearch() {
        try {
            return new Scanner(System.in).nextDouble();
        } catch (NumberFormatException e) {
            System.out.print("Error: \n");
            System.out.println(e.getMessage());
            return getDoubleToSearch();
        }
    }

    private static void searchTestsWithCoefficient() {
        System.out.println("Enter test coefficient to search");
        double coefficient = getDoubleToSearch();
        int optionCode = getTestsSortingOption();
        try {
            printSearchedTestsByCoefficient(PAGE_SIZE, 0, coefficient, optionCode);
        } catch (NoElementsFoundException e) {
            System.out.print("Error: \n");
            System.out.println(e.getMessage());
        }
    }

    private static void printSearchedTestsByCoefficient(int limit, int offset, double testCoefficient, int usersSortingOption) throws IncorrectPageException {
        List<Test> tests = new TestsSearchController(usersSortingOption).searchTestByCoefficient(limit, offset, testCoefficient);
        System.out.println("Tests with coefficient " + testCoefficient + " found:");
        tests.forEach(a -> System.out.println(a.getName() + " has coefficient to pass " + a.getCoefficientToPass() +
                " from teacher " + a.getTeacher().getLastName() + " " + a.getTeacher().getFirstName() + " " + a.getTeacher().getMiddleName()));
        System.out.println("page - " + ((offset / limit) + 1) + "\n");
        PagingOptions pagingOption;
        if (offset == 0)
            pagingOption = getPagingOptionWithoutPrevPageOption();
        else pagingOption = getPagingOption();
        switch (pagingOption) {
            case PrevPage:
                try {
                    printSearchedTestsByCoefficient(limit, offset - limit, testCoefficient, usersSortingOption);
                } catch (IncorrectPageException e) {
                    System.out.println("Cant back to previous page\n");
                    printSearchedTestsByCoefficient(limit, offset, testCoefficient, usersSortingOption);
                }
                break;
            case NextPage:
                try {
                    printSearchedTestsByCoefficient(limit, offset + limit, testCoefficient, usersSortingOption);
                } catch (IncorrectPageException e) {
                    System.out.println("Cant go to next page\n");
                    printSearchedTestsByCoefficient(limit, offset, testCoefficient, usersSortingOption);
                }
                break;
            case LogOut:
                logOut();
                break;
            case Back:
                break;
            case Exit:
                exit();
                break;
        }
    }

    private static void searchTestsWithTeacherLastName() {
        System.out.println("Enter test coefficient to search");
        String teacherLastName = getStringFromKeyboard();
        int optionCode = getTestsSortingOption();
        try {
            printSearchedTestsByTeacherLastName(PAGE_SIZE, 0, teacherLastName, optionCode);
        } catch (NoElementsFoundException e) {
            System.out.print("Error: \n");
            System.out.println(e.getMessage());
        }
    }

    private static void printSearchedTestsByTeacherLastName(int limit, int offset, String teacherLastName, int usersSortingOption) throws IncorrectPageException {
        List<Test> tests = new TestsSearchController(usersSortingOption).searchTestsByTeacherLastName(limit, offset, teacherLastName);
        System.out.println("Tests of teacher with last name " + teacherLastName + " found:");
        tests.forEach(a -> System.out.println(a.getName() + " has coefficient to pass " + a.getCoefficientToPass() +
                " from teacher " + a.getTeacher().getLastName() + " " + a.getTeacher().getFirstName() + " " + a.getTeacher().getMiddleName()));
        System.out.println("page - " + ((offset / limit) + 1) + "\n");
        PagingOptions pagingOption;
        if (offset == 0)
            pagingOption = getPagingOptionWithoutPrevPageOption();
        else pagingOption = getPagingOption();
        switch (pagingOption) {
            case PrevPage:
                try {
                    printSearchedTestsByTeacherLastName(limit, offset - limit, teacherLastName, usersSortingOption);
                } catch (IncorrectPageException e) {
                    System.out.println("Cant back to previous page\n");
                    printSearchedTestsByTeacherLastName(limit, offset, teacherLastName, usersSortingOption);
                }
                break;
            case NextPage:
                try {
                    printSearchedTestsByTeacherLastName(limit, offset + limit, teacherLastName, usersSortingOption);
                } catch (IncorrectPageException e) {
                    System.out.println("Cant go to next page\n");
                    printSearchedTestsByTeacherLastName(limit, offset, teacherLastName, usersSortingOption);
                }
                break;
            case LogOut:
                logOut();
                break;
            case Back:
                break;
            case Exit:
                exit();
                break;
        }
    }
}
