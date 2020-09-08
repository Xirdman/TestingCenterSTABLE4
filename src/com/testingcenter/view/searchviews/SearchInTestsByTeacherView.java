package com.testingcenter.view.searchviews;

import com.testingcenter.controller.SearchController;
import com.testingcenter.model.Test;
import com.testingcenter.view.UserMenu;

import java.util.List;

/**
 * Class to print menu to search in tests by test teacher
 *
 * @author Matveev Alexander
 */
public class SearchInTestsByTeacherView extends UserMenu {
    /**
     * Method to print menu to search in tests by test teacher
     */
    public static void printSearchByTeacher() {
        System.out.print("Choose option you want to do \n");
        System.out.print("1 - Search by teacher first name \n");
        System.out.print("2 - Search by teacher last name \n");
        System.out.print("3 - Back to previous menu\n");
        System.out.print("4 - Log out \n");
        System.out.print("5 - Exit \n");
        int i = 0;
        try {
            i = getIntFromKeyboard();
            System.out.print("\n");
        } catch (Exception e) {
            System.out.print("Waiting to choose an option from 1 to 5 \n");
        }
        switch (i) {
            case 1:
                searchTestsByTeacherFirstName();
                break;
            case 2:
                searchTestsByTeacherLastName();
                break;
            case 3:
                SearchInTestsView.printSearchInTests();
                break;
            case 4:
                logOut();
                break;
            case 5:
                exit();
                break;
            default:
                System.out.print("Waiting to choose an option from 1 to 8 \n");
                printSearchByTeacher();
        }
    }

    private static void searchTestsByTeacherFirstName() {
        System.out.print("Enter teacher first name:");
        String teacherFirstName;
        try {
            teacherFirstName = getStringFromKeyboard();
            SearchController searchController = new SearchController();
            List<List<Test>> searchedTests = searchController.searchTestsByTeacherFirstNameByPages(PAGE_SIZE, teacherFirstName);
            if (searchedTests.get(0).isEmpty())
                System.out.println("Tests with teacher first name " + teacherFirstName + " not found:");
            else {
                System.out.println("Tests with teacher first name " + teacherFirstName + " found:");
                printTestsListByPage(0, searchedTests);
            }
        } catch (Exception e) {
            System.out.print("Invalid input");
            searchTestsByTeacherFirstName();
        }
    }

    private static void searchTestsByTeacherLastName() {
        System.out.print("Enter teacher last name:");
        String teacherLastName;
        try {
            teacherLastName = getStringFromKeyboard();
            SearchController searchController = new SearchController();
            List<List<Test>> searchedTests = searchController.searchTestsByTeacherLastNameByPage(PAGE_SIZE, teacherLastName);
            if (searchedTests.get(0).isEmpty())
                System.out.println("Tests with teacher last name " + teacherLastName + " not found:");
            else {
                System.out.println("Tests with teacher last name " + teacherLastName + " found:");
                printTestsListByPage(0, searchedTests);
            }
        } catch (Exception e) {
            System.out.print("Invalid input");
            searchTestsByTeacherLastName();
        }
    }
}
