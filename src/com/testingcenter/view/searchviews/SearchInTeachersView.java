package com.testingcenter.view.searchviews;

import com.testingcenter.controller.SearchController;
import com.testingcenter.controller.exceptions.IncorrectPageException;
import com.testingcenter.model.Teacher;
import com.testingcenter.view.UserMenu;

import java.util.List;

/**
 * Class to print menu to search in teachers
 *
 * @author Matveev Alexander
 */
public class SearchInTeachersView extends UserMenu {
    /**
     * Method to print menu to search in teachers
     */
    public static void printSearchInTeachers() {
        System.out.print("Choose option you want to do \n");
        System.out.print("1 - Search by teacher first name \n");
        System.out.print("2 - Search by teacher last name \n");
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
                searchByTeacherFirstName();
                break;
            case 2:
                searchByTeacherLastName();
                break;
            case 3:
                SearchInUsersView.printSearchInUsers();
                break;
            case 4:
                logOut();
                break;
            case 5:
                exit();
                break;
            default:
                System.out.print("Waiting to choose an option from 1 to 5 \n");
                printSearchInTeachers();
        }
    }

    private static void searchByTeacherFirstName() {
        System.out.print("Enter teacher first name:");
        String teacherFirstName;
        try {
            teacherFirstName = getStringFromKeyboard();
            SearchController searchController = new SearchController();
            List<List<Teacher>> teachers = searchController.searchTeachersByFirstNameWithPages(PAGE_SIZE, teacherFirstName);
            if (teachers.get(0).isEmpty())
                System.out.println("Teachers with first name " + teacherFirstName + " not found");
            else {
                System.out.println("Teachers with first name " + teacherFirstName + " found:");
                printTeacherListByPage(0, teachers);
            }
        } catch (Exception e) {
            System.out.print("Invalid input");
            searchByTeacherFirstName();
        }
    }

    private static void searchByTeacherLastName() {
        System.out.print("Enter teacher last name:");
        String teacherLastName;
        try {
            teacherLastName = getStringFromKeyboard();
            SearchController searchController = new SearchController();
            List<List<Teacher>> teachers = searchController.searchTeachersByTeacherLastNameWithPages(PAGE_SIZE, teacherLastName);
            if (teachers.get(0).isEmpty())
                System.out.println("Teachers with last name " + teacherLastName + " not found");
            else {
                System.out.println("Teachers with last name " + teacherLastName + " found:");
                printTeacherListByPage(0, teachers);
            }
        } catch (Exception e) {
            System.out.print("Invalid input");
            searchByTeacherLastName();
        }
    }

    private static void printTeacherListByPage(int page, List<List<Teacher>> teachers) {
        if (page >= teachers.size() || page < 0)
            throw new IncorrectPageException("Page " + (page + 1) + " not found");
        System.out.println("Teachers page number " + (page + 1) + ":");
        List<Teacher> userPageToPrint = teachers.get(page);
        for (Teacher teacher : userPageToPrint) {
            System.out.println("Teacher first name - " + teacher.getFirstName() + " and last name - " + teacher.getLastName());
        }
        System.out.println();
        choosePageOfTeachersCollectionToPrint(page, teachers);
    }

    private static void choosePageOfTeachersCollectionToPrint(int page, List<List<Teacher>> teachers) {
        PagingOptions chosenOption;
        if (page == 0) {
            if (page == teachers.size() - 1) {
                System.out.println("It is the only page\n");
                chosenOption = PagingOptions.Back;
            } else {
                chosenOption = chooseOptionWithoutPrevPage();
            }
        } else {
            if (page == teachers.size() - 1) {
                chosenOption = chooseOptionWithoutNextPage();
            } else {
                chosenOption = choosePagingOption();
            }
        }
        switch (chosenOption) {
            case PrevPage:
                printTeacherListByPage(page - 1, teachers);
                break;
            case NextPage:
                printTeacherListByPage(page + 1, teachers);
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
                choosePageOfTeachersCollectionToPrint(page, teachers);
        }
    }
}
