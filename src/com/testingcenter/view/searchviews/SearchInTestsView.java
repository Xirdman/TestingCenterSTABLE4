package com.testingcenter.view.searchviews;

import com.testingcenter.controller.SearchController;
import com.testingcenter.model.Test;
import com.testingcenter.view.UserMenu;

import java.util.List;

/**
 * Class to print menu to search in tests
 */
public class SearchInTestsView extends UserMenu {
    /**
     * Method to print menu to search in test
     */
    public static void printSearchInTests() {
        System.out.print("Choose option you want to do \n");
        System.out.print("1 - Search in test name \n");
        System.out.print("2 - Search by number of questions \n");
        System.out.print("3 - Search by test teacher \n");
        System.out.print("4 - Search in test questions \n");
        System.out.print("5 - Back to previous menu\n");
        System.out.print("6 - Log out \n");
        System.out.print("7 - Exit \n");
        int i = 0;
        try {
            i = getIntFromKeyboard();
            System.out.print("\n");
        } catch (Exception e) {
            System.out.print("Waiting to choose an option from 1 to 8 \n");
        }
        SearchController searchController = new SearchController();
        switch (i) {
            case 1:
                String searchedName = getSearchedString();
                List<List<Test>> tests = searchController.searchTestByNameWithPages(PAGE_SIZE, searchedName);
                printTestSearchResult2(searchedName, tests);
                break;
            case 2:
                int numberOfQuestionsInSearch = getNumberOfQuestionToSearch();
                printTestSearchResult2(numberOfQuestionsInSearch,
                        searchController.searchTestsByNumberOfQuestionsWithPages(PAGE_SIZE, numberOfQuestionsInSearch));
                break;
            case 3:
                SearchInTestsByTeacherView.printSearchByTeacher();
                break;
            case 4:
                SearchInTestQuestionsView.printSearchInQuestions();
                break;
            case 5:
                break;
            case 6:
                logOut();
                break;
            case 7:
                exit();
                break;
            default:
                System.out.print("Waiting to choose an option from 1 to 8 \n");
                printSearchInTests();
        }
    }

    private static void printTestSearchResult2(String searchedWord, List<List<Test>> tests) {
        if (tests.get(0).isEmpty())
            System.out.println("Tests with " + searchedWord + " not found");
        else {
            System.out.println("Tests with " + searchedWord + " found:");
            printTestsListByPage(0,tests);
        }
        System.out.println();
    }

    private static void printTestSearchResult2(int numberOfQuestions, List<List<Test>> tests) {
        if (tests.get(0).isEmpty())
            System.out.println("Tests with " + numberOfQuestions + " number of questions not found");
        else {
            System.out.println("Tests found with number of questions " + numberOfQuestions + ": ");
            printTestsListByPage(0,tests);
        }
        System.out.println();
    }

}
