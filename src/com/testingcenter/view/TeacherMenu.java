package com.testingcenter.view;

import com.testingcenter.controller.GroupController;
import com.testingcenter.controller.TestController;
import com.testingcenter.controller.exceptions.GroupNotFoundException;
import com.testingcenter.controller.exceptions.IncorrectInputException;
import com.testingcenter.controller.exceptions.IncorrectPageException;
import com.testingcenter.model.*;

import java.util.List;
import java.util.Scanner;

/**
 * View class for Teacher User
 *
 * @author Matveev Alexander
 */
public class TeacherMenu extends UserMenu {
    /**
     * First screen menu of teacher after log in
     *
     * @param teacher Teacher to show first screen menu
     */
    public static void showFirstScreen(Teacher teacher) {
        System.out.println("Hello " + teacher.getFirstName() + " " + teacher.getLastName());
        System.out.print("Choose options you want to do \n");
        System.out.print("1 - Watch tests and results \n");
        System.out.print("2 - Print groups\n");
        System.out.print("3 - Print group results\n");
        System.out.print("4 - To search\n");
        System.out.print("5 - Log out \n");
        System.out.print("6 - Exit \n");
        int i = 0;
        try {
            i = getIntFromKeyboard();
            System.out.print("\n");
        } catch (IncorrectInputException e) {
            System.out.print("Error: \n");
            System.out.print(e.getMessage());
            showFirstScreen(teacher);
        }
        switch (i) {
            case 1:
                displayTeachersTestsWithPages(teacher);
                showFirstScreen(teacher);
                break;
            case 2:
                printGroupsWithPageSize();
                showFirstScreen(teacher);
                break;
            case 3:
                printMessageBeforeRating();
                showFirstScreen(teacher);
                break;
            case 4:
                printSearchMenu();
                showFirstScreen(teacher);
                break;
            case 5:
                logOut();
                break;
            case 6:
                System.exit(0);
                break;
            default:
                System.out.print("Waiting to choose an option from 1 to 6 \n");
                showFirstScreen(teacher);
        }
    }

    private static void printMessageBeforeRating() {
        System.out.println("Enter the identifier of group to print");
        Scanner scanner = new Scanner(System.in);
        int groupId = Integer.parseInt(scanner.next());
        try {
            new GroupController().isGroupExists(groupId);
            printGroupRatingDesc2(groupId);
            System.out.println();
        } catch (GroupNotFoundException e) {
            System.out.println("Error:\n" + e.getMessage());
        }
    }

    private static void displayTeachersTestsWithPages(Teacher teacher) {
        int sort = getTestsSortingOption();
        printSearchedTestsByTeacher(PAGE_SIZE, 0, teacher, sort);
    }


    private static void printGroupRatingDesc2(int groupId) {
        System.out.println("Group " + groupId + " ratings descending");
        printGroupResults(PAGE_SIZE, 0, groupId);
    }

    private static void printGroupResults(int limit, int offset, int groupId) {
        List<Student> students = new GroupController().getGroupRtings(limit, offset, groupId);
        students.forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName() + " with rating " + a.getRating()));
        PagingOptions pagingOption;
        if (offset == 0)
            pagingOption = getPagingOptionWithoutPrevPageOption();
        else pagingOption = getPagingOption();
        switch (pagingOption) {
            case PrevPage:
                try {
                    printGroupResults(limit, offset - limit, groupId);
                } catch (IncorrectPageException e) {
                    System.out.println("Cant back to previous page\n");
                    printGroupResults(limit, offset, groupId);
                }
                break;
            case NextPage:
                try {
                    printGroupResults(limit, offset + limit, groupId);
                } catch (IncorrectPageException e) {
                    System.out.println("Cant go to next page\n");
                    printGroupResults(limit, offset, groupId);
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

    private static void printSearchedTestsByTeacher(int limit, int offset, Teacher teacher, int usersSortingOption) throws IncorrectPageException {
        List<Test> tests = new TestController().getTeacherTests(limit, offset, teacher, usersSortingOption);
        System.out.println("Tests of teacher :");
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
                    printSearchedTestsByTeacher(limit, offset - limit, teacher, usersSortingOption);
                } catch (IncorrectPageException e) {
                    System.out.println("Cant back to previous page\n");
                    printSearchedTestsByTeacher(limit, offset, teacher, usersSortingOption);
                }
                break;
            case NextPage:
                try {
                    printSearchedTestsByTeacher(limit, offset + limit, teacher, usersSortingOption);
                } catch (IncorrectPageException e) {
                    System.out.println("Cant go to next page\n");
                    printSearchedTestsByTeacher(limit, offset, teacher, usersSortingOption);
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

    private static void printGroupsWithPageSize() {
        int sort = getGroupsSortingOption();
        printGroups(PAGE_SIZE, 0, sort);
    }

    private static int getGroupsSortingOption() {
        System.out.println("Choose sorting option:");
        System.out.print("1 - Sort by group identifier\n");
        System.out.print("2 - Sort by group name \n");
        System.out.print("3 - Sort by group capacity\n");
        int i = 0;
        try {
            i = getIntFromKeyboard();
            System.out.print("\n");
        } catch (IncorrectInputException e) {
            System.out.print("Error: \n");
            System.out.print(e.getMessage());
        }
        if ((i > 0) && (i < 4))
            return i;
        else {
            System.out.println("Please choose from options");
            return getGroupsSortingOption();
        }
    }

    private static void printGroups(int limit, int offset, int sortingOption) {
        List<Group> groups = new GroupController().getGroupsSorted(limit, offset, sortingOption);
        GroupController groupController = new GroupController();
        System.out.println("Groups: ");
        groups.forEach(a -> System.out.println(a.getName() + " with identifier " + a.getId() + " " + groupController.getStudentsByGroup(a.getId()).size() + " students in this group"));
        PagingOptions pagingOptions;
        if (offset == 0)
            pagingOptions = getPagingOptionWithoutPrevPageOption();
        else pagingOptions = getPagingOption();
        switch (pagingOptions) {
            case PrevPage:
                try {
                    printGroups(limit, offset - limit, sortingOption);
                } catch (IncorrectPageException e) {
                    System.out.println("There is no previous page");
                    printGroups(limit, offset, sortingOption);
                }
                break;
            case NextPage:
                try {
                    printGroups(limit, offset + limit, sortingOption);
                } catch (IncorrectPageException e) {
                    System.out.println("There is no next page");
                    printGroups(limit, offset, sortingOption);
                }
                break;
            case LogOut:
                logOut();
                break;
            case Exit:
                exit();
                break;
            default:
                break;
        }
    }

}

