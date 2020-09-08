package com.testingcenter.view;

import com.testingcenter.controller.GroupController;
import com.testingcenter.controller.TestController;
import com.testingcenter.controller.exceptions.GroupNotFoundException;
import com.testingcenter.controller.exceptions.IncorrectInputException;
import com.testingcenter.controller.exceptions.IncorrectPageException;
import com.testingcenter.controller.exceptions.NoTestsForTeacherException;
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
        TestController testController = new TestController();
        try {
            List<List<Test>> tests = testController.getTeacherTestsPaged(PAGE_SIZE, teacher);
            printTestsCollectionByPageWithResults(0, tests);
        } catch (NoTestsForTeacherException | IncorrectPageException e) {
            System.out.println("Error:\n" + e.getMessage());
        }
    }

    private static void printTestsCollectionByPageWithResults(int page, List<List<Test>> tests) {
        if (page >= tests.size() || page < 0)
            throw new IncorrectPageException("Page " + (page+1) + " not found");
        System.out.println("Tests page number " + (page+1) + ":");
        List<Test> userPageToPrint = tests.get(page);
        for (Test test : userPageToPrint) {
            System.out.print("Test - " + test.getName() + "\n" + new TestController().getTestResults(test) + " \n");
        }
        System.out.println();
        chooseTestsPageToPrint(page, tests);
    }

    private static void printGroupsWithPageSize() {
        try {
            List<List<Group>> groups = new GroupController().getGroupsByPageSize(PAGE_SIZE);
            printGroupListByPage(0, groups);
        } catch (IncorrectPageException e) {
            System.out.println("Error:\n" + e.getMessage());
        }
    }

    private static void printGroupRatingDesc2(int groupId) {
        GroupController groupController = new GroupController();
        System.out.println(groupId + " raitings are:");
        List<List<Student>> students = groupController.getStudentsByGroupByPageSize(PAGE_SIZE, groupId);
        printStudentsListWithRatingDescByPage(0,students);
    }

    private static void printStudentsListWithRatingDescByPage(int page, List<List<Student>> students) {
        if (page >= students.size() || page < 0)
            throw new IncorrectPageException("Page " + (page+1) + " not found");
        System.out.println("Students rating page number " + (page+1) + ":");
        List<Student> studentsPage = students.get(page);
        for (Student student : studentsPage) {
            System.out.println(student.getFirstName()+" "+student.getLastName()+" with rating "+student.getRating());
        }
        System.out.println();
        choosePageToPrintGroupRating(page,students);
    }

    private static void choosePageToPrintGroupRating(int page, List<List<Student>> students) {
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
                printStudentsListWithRatingDescByPage(page-1,students);
                break;
            case NextPage:
                printStudentsListWithRatingDescByPage(page+1,students);
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
                choosePageToPrintGroupRating(page,students);
        }
    }

    private static void printGroupListByPage(int page, List<List<Group>> groups) {
        if (page >= groups.size() || page < 0)
            throw new IncorrectPageException("Page " + (page + 1) + " not found");
        System.out.println("Groups page number " + (page + 1) + ":");
        List<Group> groupsPage = groups.get(page);
        for (Group group : groupsPage) {
            System.out.println(group.getName() + " with identifier " + group.getId());
        }
        System.out.println();
        choosePageOfGroupsCollectionToPrint(page, groups);
    }

    private static void choosePageOfGroupsCollectionToPrint(int page, List<List<Group>> groups) {
        PagingOptions chosenOption;
        if (page == 0) {
            if (page == groups.size() - 1) {
                System.out.println("It is the only page\n");
                chosenOption = PagingOptions.Back;
            } else {
                chosenOption = chooseOptionWithoutPrevPage();
            }
        } else {
            if (page == groups.size() - 1) {
                chosenOption = chooseOptionWithoutNextPage();
            } else {
                chosenOption = choosePagingOption();
            }
        }
        switch (chosenOption) {
            case PrevPage:
                printGroupListByPage(page - 1, groups);
                break;
            case NextPage:
                printGroupListByPage(page + 1, groups);
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
                choosePageOfGroupsCollectionToPrint(page, groups);
        }
    }
}

