package com.testingcenter.view;

import com.testingcenter.Main;
import com.testingcenter.controller.SearchController;
import com.testingcenter.controller.TestQuestionController;
import com.testingcenter.model.*;

import java.util.Collection;
import java.util.List;
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
                printSearchInUsers(user);
                break;
            case 3:
                printSearchInQuestions(user);
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

    private void searchTestsByTeacherFirstName() {
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

    private void searchTestsByTeacherLastName() {
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

    private void printSearchInUsers(User user) {
        System.out.print("Choose option you want to do \n");
        System.out.print("1 - Search in students \n");
        System.out.print("2 - Search in admins \n");
        System.out.print("3 - Search in teachers \n");
        System.out.print("4 - Go to previous menu \n");
        System.out.print("5 - Log out \n");
        System.out.print("6 - Exit \n");
        int i = 0;
        try {
            i = getIntFromKeyboard();
            System.out.print("\n");
        } catch (Exception e) {
            System.out.print("Waiting to choose an option from 1 to 6 \n");
        }
        switch (i) {
            case 1:
                printSearchInStudents(user);
                break;
            case 2:
                printSearchInAdmins(user);
                break;
            case 3:
                printSearchInTeachers(user);
                break;
            case 4:
                printSearchMenu(user);
                break;
            case 5:
                logOut();
                break;
            case 6:
                exit();
                break;
            default:
                System.out.print("Waiting to choose an option from 1 to 6 \n");
                printSearchInUsers(user);
        }
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
                printSearchInUsers(user);
                break;
            case 4:
                logOut();
                break;
            case 5:
                exit();
                break;
            default:
                System.out.print("Waiting to choose an option from 1 to 5 \n");
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
            printStudentsSearchResult(studentLastName, searchController.searchByStudentLastName(studentLastName));
        } catch (Exception e) {
            System.out.print("Invalid input");
            searchByTeacherFirstName();
        }
    }

    private void printSearchInAdmins(User user) {
        System.out.print("Choose option you want to do \n");
        System.out.print("1 - Search by Admin first name \n");
        System.out.print("2 - Search by Admin last name \n");
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
                searchByAdminFirstName();
                break;
            case 2:
                searchByAdminLastName();
                break;
            case 3:
                printSearchInUsers(user);
                break;
            case 4:
                logOut();
                break;
            case 5:
                exit();
                break;
            default:
                System.out.print("Waiting to choose an option from 1 to 5 \n");
                printSearchInStudents(user);
        }
    }

    private void searchByAdminFirstName() {
        System.out.print("Enter admin first name:");
        String adminFirstName;
        try {
            adminFirstName = getStringFromKeyboard();
            SearchController searchController = new SearchController();
            printAdminSearchResult(adminFirstName, searchController.searchByAdminFirstName(adminFirstName));
        } catch (Exception e) {
            System.out.print("Invalid input");
            searchByTeacherFirstName();
        }
    }

    private void printAdminSearchResult(String searchedWord, Collection<Admin> admins) {
        if (admins.isEmpty())
            System.out.println("Admin with " + searchedWord + " not found");
        else {
            System.out.println("Admins found:");
            printAdminsCollection(admins);
        }
    }

    private void printAdminsCollection(Collection<Admin> admins) {
        for (Admin admin : admins)
            System.out.println(admin.getFirstName() + " " + admin.getLastName());
    }

    private void searchByAdminLastName() {
        System.out.print("Enter admin last name:");
        String adminLastName;
        try {
            adminLastName = getStringFromKeyboard();
            SearchController searchController = new SearchController();
            printAdminSearchResult(adminLastName, searchController.searchByAdminLastName(adminLastName));
        } catch (Exception e) {
            System.out.print("Invalid input");
            searchByTeacherFirstName();
        }
    }

    private void printSearchInTeachers(User user) {
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
                printSearchInUsers(user);
                break;
            case 4:
                logOut();
                break;
            case 5:
                exit();
                break;
            default:
                System.out.print("Waiting to choose an option from 1 to 5 \n");
                printSearchInStudents(user);
        }
    }

    private void searchByTeacherFirstName() {
        System.out.print("Enter teacher first name:");
        String teacherFirstName;
        try {
            teacherFirstName = getStringFromKeyboard();
            SearchController searchController = new SearchController();
            printTeacherSearchResult(teacherFirstName, searchController.searchByTeacherFirstName(teacherFirstName));
        } catch (Exception e) {
            System.out.print("Invalid input");
            searchByTeacherFirstName();
        }
    }

    private void printTeacherSearchResult(String searchedWord, Collection<Teacher> teachers) {
        if (teachers.isEmpty())
            System.out.println("Admin with " + searchedWord + " not found");
        else {
            System.out.println("Admins found:");
            printTeacherCollection(teachers);
        }
    }

    private void printTeacherCollection(Collection<Teacher> teachers) {
        for (Teacher teacher : teachers)
            System.out.println("Teacher - " + teacher.getFirstName() + " " + teacher.getLastName());
    }

    private void searchByTeacherLastName() {
        System.out.print("Enter teacher last name:");
        String teacherLastName;
        try {
            teacherLastName = getStringFromKeyboard();
            SearchController searchController = new SearchController();
            printTeacherSearchResult(teacherLastName, searchController.searchByTeacherLastName(teacherLastName));
        } catch (Exception e) {
            System.out.print("Invalid input");
            searchByTeacherFirstName();
        }
    }

    private void printSearchInQuestions(User user) {
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
                printSearchMenu(user);
                break;
            case 4:
                logOut();
                break;
            case 5:
                exit();
                break;
            default:
                System.out.print("Waiting to choose an option from 1 to 5 \n");
                printSearchInQuestions(user);
        }
    }

    private void printSearchInTestQuestionText() {
        System.out.print("Enter searched word:");
        try {
            String searchedWord = getStringFromKeyboard();
            printQuestionsSearchResult(searchedWord, new SearchController().searchTestQuestionsByWordInText(searchedWord));
        } catch (Exception e) {
            System.out.print("Exception:\n "+ e.getMessage());
            printSearchInTestQuestionText();
        }
    }

    private void printSearchInTestQuestionAnswerText() {
        System.out.print("Enter searched word:");
        try {
            String searchedWord = getStringFromKeyboard();
            printQuestionsSearchResult(searchedWord, new SearchController().searchTestQuestionsByWordInText(searchedWord));
        } catch (Exception e) {
            System.out.print("Exception:\n "+ e.getMessage());
            printSearchInTestQuestionAnswerText();
        }
    }
    private void printQuestionsSearchResult(String searchedWord, Collection<TestQuestion> questions){
        if(questions.isEmpty()){
            System.out.println("Questions with "+ searchedWord+" not found");
        }else {
            printQuestionsCollection(questions);
            System.out.println("");
        }
    }
    private void printQuestionsCollection(Collection<TestQuestion> questions){
        for(TestQuestion question: questions){
            System.out.println(question.getQuestionText());
            List<QuestionAnswer> answers =new TestQuestionController().getQuestionAnswers(question);
            for(QuestionAnswer answer:answers)
                System.out.println("Answer - "+answer.getText()+" and give "+answer.getPoints()+" points");
            System.out.println("");
        }
    }

}
