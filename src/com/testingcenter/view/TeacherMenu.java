package com.testingcenter.view;

import com.testingcenter.Main;
import com.testingcenter.controller.GroupController;
import com.testingcenter.controller.TestController;
import com.testingcenter.model.*;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * View class for Teacher User
 *
 * @author Matveev Alexander
 */
public class TeacherMenu {
    /**
     * First screen menu of teacher after log in
     *
     * @param teacher Teacher to show first screen menu
     */
    public static void showFirstScreen(Teacher teacher) {
        System.out.print("Choose options you want to do \n");
        System.out.print("1 - Watch tests and results \n");
        System.out.println("2 - Print groups");
        System.out.println("3 - Print group results");
        System.out.print("4 - Log out \n");
        System.out.print("5 - Exit \n");
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        try {
            i = Integer.parseInt(scanner.next());
            System.out.print("\n");
        } catch (Exception e) {
            showFirstScreen(teacher);
        }
        switch (i) {
            case 1:
                displayTeachersTests(teacher);
                showFirstScreen(teacher);
                break;
            case 2:
                printGroups();
                showFirstScreen(teacher);
                break;
            case 4:
                Main.showWelcomeScreen();
                break;
            case 3:
                printMessageBeforeRaiting();
                showFirstScreen(teacher);
                break;
            case 5:
                scanner.close();
                System.exit(0);
                break;
            default:
                System.out.print("Waiting to choose an option from 1 to 3 \n");
                showFirstScreen(teacher);
        }
    }

    private static void displayTeachersTests(Teacher teacher) {
        TestController testController = new TestController();
        List<Test> tests = testController.getTeachersTests(teacher);
        Iterator<Test> iterator = tests.iterator();
        while (iterator.hasNext()) {
            Test test = iterator.next();
            System.out.print("Test - " + test.getName() + "\n");
            System.out.print(testController.getTestResults(test) + " \n");
        }
        System.out.println();
    }

    private static void printMessageBeforeRaiting() {
        System.out.println("Enter the identificator of group to print");
        Scanner scanner = new Scanner(System.in);
        int groupId = Integer.parseInt(scanner.next());
        if (new GroupController().isGroupExists(groupId)) {
            printGroupRatingDesc(groupId);
        }
        System.out.println();
    }

    private static void printGroupRatingDesc(int groupId) {
        System.out.println(groupId + " raitings are:");
        List<Student> students = new GroupController().getGroupRatings(groupId);
        for(Student student: students){
            System.out.println(student.getFirstName()+" have rating " + student.getRating());
        }
        System.out.println();
    }

    private static void printGroups() {
        List<Group> groups = new GroupController().getGroups();
        Iterator<Group> iterator = groups.iterator();
        System.out.println("Groups:");
        while (iterator.hasNext()) {
            Group groupToPrint = iterator.next();
            System.out.println(groupToPrint.getName() + " with identificator " + groupToPrint.getId());
        }
        System.out.println();
    }
}
