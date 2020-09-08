package com.testingcenter.view.searchviews;

import com.testingcenter.controller.SearchController;
import com.testingcenter.controller.exceptions.IncorrectPageException;
import com.testingcenter.model.TestQuestion;
import com.testingcenter.view.UserMenu;

import java.util.List;

/**
 * Class to print menu to search in all questions
 *
 * @author Matveev Alexander
 */
public class SearchInTestQuestionsView extends UserMenu {
    /**
     * Method to print menu to search in tests
     */
    public static void printSearchInQuestions() {
        System.out.print("Choose option you want to do \n");
        System.out.print("1 - Search in question text \n");
        System.out.print("2 - Search in question answers \n");
        System.out.print("3 - Go to previous menu \n");
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
                printSearchInTestQuestionText();
                break;
            case 2:
                printSearchInTestQuestionAnswerText();
                break;
            case 3:
                break;
            case 4:
                logOut();
                break;
            case 5:
                exit();
                break;
            default:
                System.out.print("Waiting to choose an option from 1 to 5 \n");
                printSearchInQuestions();
        }
    }

    private static void printTestQuestionsListByPage(int page, List<List<TestQuestion>> testQuestions) {
        if (page >= testQuestions.size() || page < 0)
            throw new IncorrectPageException("Page " + (page + 1) + " not found");
        else {
            System.out.println("Users page number " + page + ":");
            List<TestQuestion> userPageToPrint = testQuestions.get(page);
            for (TestQuestion testQuestion : userPageToPrint) {
                System.out.println("Text of question : " + testQuestion.getQuestionText() +
                        " from test " + testQuestion.getQuestionTest().getName());
            }
        }
        System.out.println();
        choosePageOfTestQuestionsCollectionToPrint(page, testQuestions);
    }

    private static void choosePageOfTestQuestionsCollectionToPrint(int page, List<List<TestQuestion>> testQuestions) {
        PagingOptions chosenOption;
        if (page == 0) {
            if (page == testQuestions.size() - 1) {
                System.out.println("It is the only page\n");
                chosenOption = PagingOptions.Back;
            } else {
                chosenOption = chooseOptionWithoutPrevPage();
            }
        } else {
            if (page == testQuestions.size() - 1) {
                chosenOption = chooseOptionWithoutNextPage();
            } else {
                chosenOption = choosePagingOption();
            }
        }
        switch (chosenOption) {
            case PrevPage:
                printTestQuestionsListByPage(page - 1, testQuestions);
                break;
            case NextPage:
                printTestQuestionsListByPage(page + 1, testQuestions);
                break;
            case Back:
                break;
            case LogOut:
                logOut();
                break;
            case Exit:
                exit();
                break;
            default:
                choosePageOfTestQuestionsCollectionToPrint(page, testQuestions);
        }
    }

    private static void printSearchInTestQuestionText() {
        System.out.print("Enter searched word:");
        try {
            String searchedWord = getStringFromKeyboard();
            List<List<TestQuestion>> testQuestions = new SearchController()
                    .searchTestQuestionsByWordInTextWithPages(PAGE_SIZE, searchedWord);
            if (testQuestions.get(0).isEmpty()) {
                System.out.println("tests with word " + searchedWord + " in question text not found");
            } else {
                System.out.println("tests with word " + searchedWord + " in question text found");
                printTestQuestionsListByPage(0, testQuestions);
            }
        } catch (Exception e) {
            System.out.print("Exception:\n " + e.getMessage());
            printSearchInTestQuestionText();
        }
    }

    private static void printSearchInTestQuestionAnswerText() {
        System.out.print("Enter searched word to search in test questions answers:");
        try {
            String searchedWord = getStringFromKeyboard();
            List<List<TestQuestion>> testQuestions = new SearchController().searchTestQuestionsByWordInAnswersWithPages(PAGE_SIZE, searchedWord);
            if (testQuestions.get(0).isEmpty()) {
                System.out.println("tests with word " + searchedWord + " in answer text not found");
            } else {
                System.out.println("tests with word " + searchedWord + " in answer text found");
                printTestQuestionsListByPage(0, testQuestions);
            }
        } catch (Exception e) {
            System.out.print("Exception:\n " + e.getMessage());
            printSearchInTestQuestionText();
        }
    }
}
