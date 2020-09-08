package com.testingcenter.view.searchviews;

import com.testingcenter.controller.SearchController;
import com.testingcenter.controller.exceptions.IncorrectPageException;
import com.testingcenter.model.Student;
import com.testingcenter.view.UserMenu;

import java.util.List;

/**
 * Class to print menu to search in students
 *
 * @author Matveev Alexander
 */
public class SearchInStudentsView extends UserMenu {
    /**
     * Method to print menu to search in students
     */
    public static void printSearchInStudents() {
        System.out.print("Choose option you want to do \n");
        System.out.print("1 - Search by Student first name \n");
        System.out.print("2 - Search by Student last name \n");
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
                searchByStudentFirstName();
                break;
            case 2:
                searchByStudentLastName();
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
                printSearchInStudents();
        }
    }

    private static void searchByStudentFirstName() {
        System.out.print("Enter student first name:");
        String studentFirstName;
        try {
            studentFirstName = getStringFromKeyboard();
            SearchController searchController = new SearchController();
            List<List<Student>> students = searchController.searchStudentsByStudentFirstNameWithPages(PAGE_SIZE, studentFirstName);
            if (students.get(0).isEmpty()) {
                System.out.println("Students with first name " + studentFirstName + " not found");
            } else {
                System.out.println("Students with first name " + studentFirstName + " found:");
                printStudentsListByPage(0, students);
            }
        } catch (Exception e) {
            System.out.print("Invalid input");
            searchByStudentFirstName();
        }
    }

    private static void searchByStudentLastName() {
        System.out.print("Enter student last name:");
        String studentLastName;
        try {
            studentLastName = getStringFromKeyboard();
            SearchController searchController = new SearchController();
            List<List<Student>> students = searchController.searchStudentsByStudentLastNameWithPages(PAGE_SIZE, studentLastName);
            if (students.get(0).isEmpty()) {
                System.out.println("Students with last name " + studentLastName + " not found");
            } else {
                System.out.println("Students with last name " + studentLastName + " found:");
                printStudentsListByPage(0, students);
            }
        } catch (Exception e) {
            System.out.print("Invalid input");
            searchByStudentLastName();
        }
    }

    private static void printStudentsListByPage(int page, List<List<Student>> students) {
        if (page >= students.size() || page < 0)
            throw new IncorrectPageException("Page " + (page + 1) + " not found");
        System.out.println("Students page number " + (page + 1) + ":");
        List<Student> userPageToPrint = students.get(page);
        for (Student student : userPageToPrint) {
            System.out.println(student.getFirstName() + " " + student.getLastName() + " with rating" + student.getRating());
        }
        System.out.println();
        choosePageOfStudentsCollectionToPrint(page, students);
    }

    private static void choosePageOfStudentsCollectionToPrint(int page, List<List<Student>> students) {
        PagingOptions chosenOption;
        if (page == 0) {
            if (page == students.size() - 1) {
                System.out.println("It is the only page\n");
                chosenOption = PagingOptions.Back;
            } else {
                chosenOption = chooseOptionWithoutPrevPage();
            }
        } else {
            if (page == students.size() - 1) {
                chosenOption = chooseOptionWithoutNextPage();
            } else {
                chosenOption = choosePagingOption();
            }
        }
        switch (chosenOption) {
            case PrevPage:
                printStudentsListByPage(page - 1, students);
                break;
            case NextPage:
                printStudentsListByPage(page + 1, students);
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
                choosePageOfStudentsCollectionToPrint(page, students);
        }
    }
}
