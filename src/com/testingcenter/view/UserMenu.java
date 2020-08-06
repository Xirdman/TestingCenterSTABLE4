package com.testingcenter.view;

import com.testingcenter.Main;
import com.testingcenter.controller.SearchController;
import com.testingcenter.model.Student;
import com.testingcenter.model.Test;
import com.testingcenter.model.User;

import java.util.Collection;
import java.util.Scanner;

public class UserMenu {
    void logOut() {
        Main.showWelcomeScreen();
    }

    void exit() {
        System.exit(0);
    }

    void printSearchMenu(User user) {
        System.out.print("Choose where you want to search \n");
        System.out.print("1 - Search in tests\n");
        System.out.print("2 - Search in users \n");
        System.out.print("3 - Search in questions \n");
        System.out.print("4 - Search in test answers\n");
        System.out.print("5 - Back to previous menu\n");
        System.out.print("6 - Log out\n");
        System.out.print("7 - Exit\n");
        int i = 0;
        try {
            i = getIntFromKeyboard();
            System.out.print("\n");
        } catch (Exception e) {
            System.out.print("Waiting to choose an option from 1 to 7 \n");
        }
        switch (i) {
            case 1:
                printSearchInTests(user);
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                exit();
                break;
            default:
                System.out.print("Waiting to choose an option from 1 to 7 \n");
                printSearchMenu(user);
        }
    }

    private void printSearchInTests(User user) {
        System.out.print("Choose option you want to do \n");
        System.out.print("1 - Search in test name \n");
        System.out.print("2 - Search by number of questions \n");
        System.out.print("3 - Search by test teacher \n");
        System.out.print("4 - Search in test questions \n");
        System.out.print("5 - Go to previous menu \n");
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
                printTestSearchResult(searchedName,
                        searchController.searchTestsByName(searchedName));
                break;
            case 2:
                int numberOfQuestionsInSearch = getNumberOfQuestionToSearch();
                printTestSearchResult(numberOfQuestionsInSearch,
                        searchController.searchTestByNumberOfQuestions(numberOfQuestionsInSearch));
                break;
            case 3:
                printSearchByTeacher(user);
                break;
            case 4:
                searchByWordInTestQuestion();
                break;
            case 5:
                printSearchMenu(user);
                break;
            case 6:
                logOut();
                break;
            case 7:
                exit();
                break;
            default:
                System.out.print("Waiting to choose an option from 1 to 8 \n");
                printSearchInTests(user);
        }
    }

    private String getSearchedString() {
        System.out.println("Enter the word you want to search: ");
        return getStringFromKeyboard();
    }

    String getStringFromKeyboard() {
        Scanner scanner = new Scanner(System.in);
        String result = scanner.next();
        return result;
    }

    private int getNumberOfQuestionToSearch() {
        System.out.println("Enter number of questions: ");
        try {
            return getIntFromKeyboard();
        } catch (Exception e) {
            System.out.println("Invalid input");
            return getNumberOfQuestionToSearch();
        }
    }

    int getIntFromKeyboard() {
        Scanner scanner = new Scanner(System.in);
        String result = scanner.next();
        try {
            return Integer.parseInt(result);
        } catch (NumberFormatException e) {
            // Custom exception from Se-5
            throw e;
        }
    }

    private void printTestSearchResult(String searchedWord, Collection<Test> tests) {
        if (tests.isEmpty())
            System.out.println("Tests with " + searchedWord + " not found");
        else {
            System.out.println("Tests found: ");
            printTestCollection(tests);
        }
        System.out.println();
    }

    private void printTestSearchResult(int numberOfQuestions, Collection<Test> tests) {
        if (tests.isEmpty())
            System.out.println("Tests with " + numberOfQuestions + " number of questions not found");
        else {
            System.out.println("Tests found: ");
            printTestCollection(tests);
        }
        System.out.println();
    }

    private void printTestCollection(Collection<Test> tests) {
        for (Test test : tests)
            System.out.println(test.getName()
                    + " from teacher " + test.getTeacher().getFirstName() + " " + test.getTeacher().getLastName()
                    + " with coefficient to pass " + test.getCoefficientToPass());
    }

    private void printSearchByTeacher(User user) {
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
                printSearchInTests(user);
                break;
            case 4:
                logOut();
                break;
            case 5:
                exit();
                break;
            default:
                System.out.print("Waiting to choose an option from 1 to 8 \n");
                printSearchByTeacher(user);
        }
    }

    private void searchByTeacherFirstName() {
        System.out.print("Enter teacher first name:");
        String teacherName;
        try {
            teacherName = getStringFromKeyboard();
            SearchController searchController = new SearchController();
            printTestSearchResult(teacherName,
                    searchController.searchTestsByTeacherFirstName(teacherName));
        } catch (Exception e) {
            System.out.print("Invalid input");
            searchByTeacherFirstName();
        }
    }

    private void searchByTeacherLastName() {
        System.out.print("Enter teacher last name:");
        String teacherName;
        try {
            teacherName = getStringFromKeyboard();
            SearchController searchController = new SearchController();
            printTestSearchResult(teacherName,
                    searchController.searchTestsByTeacherLastName(teacherName));
        } catch (Exception e) {
            System.out.print("Invalid input");
            searchByTeacherFirstName();
        }
    }

    private void searchByWordInTestQuestion() {
        System.out.println("Add realisation");
    }

    private void printSearchInStudents(User user) {
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
                printSearchMenu(user);
                break;
            case 4:
                logOut();
                break;
            case 5:
                exit();
                break;
            default:
                System.out.print("Waiting to choose an option from 1 to 8 \n");
                printSearchInStudents(user);
        }
    }

    private void searchByStudentFirstName() {
        System.out.print("Enter student first name:");
        String studentFirstName;
        try {
            studentFirstName = getStringFromKeyboard();
            SearchController searchController = new SearchController();
            printStudentsSearchResult(studentFirstName, searchController.searchByStudentFirstName(studentFirstName));
        } catch (Exception e) {
            System.out.print("Invalid input");
            searchByTeacherFirstName();
        }
    }

    private void printStudentsSearchResult(String searchedWord, Collection<Student> students) {
        if (students.isEmpty())
            System.out.println("Students with " + searchedWord + " not found");
        else {
            System.out.println("Students found:");
            printStudentsCollection(students);
        }
    }

    private void printStudentsCollection(Collection<Student> students) {
        for (Student student : students)
            System.out.println(student.getFirstName() + " "
                    + student.getLastName() + " with raiting "
                    + student.getRating() +
                    " from group with id - " + student.getGroupId());
    }

    private void searchByStudentLastName() {
        System.out.print("Enter student last name:");
        String studentLastName;
        try {
            studentLastName = getStringFromKeyboard();
            SearchController searchController = new SearchController();
            printStudentsSearchResult(studentLastName, searchController.searchByStudentFirstName(studentLastName));
        } catch (Exception e) {
            System.out.print("Invalid input");
            searchByTeacherFirstName();
        }
    }

}
