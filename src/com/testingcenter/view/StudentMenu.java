package com.testingcenter.view;

import com.testingcenter.controller.StudentController;
import com.testingcenter.controller.TestController;
import com.testingcenter.controller.TestQuestionController;
import com.testingcenter.controller.exceptions.AssignmentNotFoundException;
import com.testingcenter.controller.exceptions.IncorrectInputException;
import com.testingcenter.controller.exceptions.IncorrectPageException;
import com.testingcenter.controller.exceptions.StudentTestNotFoundException;
import com.testingcenter.model.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


/**
 * View class for admin menu
 *
 * @author Matveev Alexander
 */
public class StudentMenu extends UserMenu {

    /**
     * First Screen of student menu after log in
     *
     * @param student Student to display menu after log in
     */
    public static void showFirstScreen(Student student) {
        System.out.println("Hello " + student.getFirstName() + " " + student.getLastName());
        System.out.print("Choose options you want to do \n");
        System.out.print("1 - Watch your tests and how many questions in it \n");
        System.out.print("2 - To complete test\n");
        System.out.print("3 - To search\n");
        System.out.print("4 - Log out \n");
        System.out.print("5 - Exit \n");
        int i = 0;
        try {
            i = getIntFromKeyboard();
            System.out.print("\n");
        } catch (IncorrectInputException e) {
            System.out.print("Error: \n");
            System.out.print(e.getMessage());
            showFirstScreen(student);
        }
        switch (i) {
            case 1:
                showTestsCollectionByPage(student);
                showFirstScreen(student);
                break;
            case 2:
                startCompleteTest(student);
                showFirstScreen(student);
                break;
            case 3:
                printSearchMenu();
                showFirstScreen(student);
                break;
            case 4:
                logOut();
                break;
            case 5:
                System.exit(0);
                break;
            default:
                System.out.print("Waiting to choose an option from 1 to 4 \n");
                showFirstScreen(student);
        }
    }

    private static void startCompleteTest(Student student) {
        try {
            List<Test> list = new StudentController().getStudentTests(student);
            chooseTest(student, list);
        } catch (StudentTestNotFoundException e) {
            System.out.println("Error:\n" + e.getMessage());
        }
    }

    private static void chooseTest(Student student, List<Test> studentUncompletedTests) {
        System.out.println("Choose test to complete");
        int i = 1;
        for (Test test : studentUncompletedTests) {
            System.out.println(i + " - to complete " + test.getName());
            i++;
        }
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.next();
        try {
            int choosenTestPosition = new StudentController().checkIntegerInputFromZeroToValue(inputString, i);
            completeTest(student, studentUncompletedTests.get(choosenTestPosition - 1));
        } catch (IncorrectInputException e) {
            System.out.println("Error:\n" + e.getMessage());
        }
    }

    private static void completeTest(Student student, Test test) {
        if (new TestController()
                .getTestQuestions(test)
                .stream().allMatch(p -> printAnswersForQuestion(p, student))) {
            System.out.println("\nCongratulations! You answer to all questions from " + test.getName() + "\n");
            try {
                Assignment assignment = new StudentController().getAssignmentForStudentsTest(student, test);
                assignment.setIsCompleted(true);
                printResultsAfterTest(assignment);
            } catch (AssignmentNotFoundException e) {
                System.out.println("Error:\n" + e.getMessage());
            }
        }

    }

    private static boolean printAnswersForQuestion(TestQuestion question, Student student) {
        System.out.println("Question text: \n" + question.getQuestionText());
        TestQuestionController testQuestionController = new TestQuestionController();
        List<QuestionAnswer> answers = testQuestionController.getQuestionAnswers(question);
        int i = 1;
        for (QuestionAnswer questionAnswer : answers) {
            System.out.println(i + " - " + questionAnswer.getText());
            i++;
        }
        i--;
        System.out.print("0 - to return to the main menu \n" +
                "if you choose more than one answer please enter them using space \n");
        Scanner scanner = new Scanner(System.in);
        String[] answerString = scanner.nextLine().split(" ");
        if (Arrays.asList(answerString).contains("0")) {
            return false;
        }
        try {
            int[] ints = Arrays.stream(answerString).mapToInt(Integer::parseInt).toArray();
            for (int j : ints) {
                if (j < 0) {
                    throw new NumberFormatException("Answer " + j + " is negative\n");
                } else {
                    if (j > i) {
                        throw new NumberFormatException("Answer " + j + " is bigger than number of options\n");
                    }
                }
            }
            testQuestionController.processStudentAnswer(ints, student, answers, question);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return printAnswersForQuestion(question, student);
        }
        return true;
    }

    private static void printResultsAfterTest(Assignment assignment) {
        TestController testController = new TestController();
        Test test = assignment.getTest();
        Student student = assignment.getStudent();
        TestResult testResult = testController.createTestResult(student, test);
        int studentResult = testResult.getPoints();
        int maxTestPoints = testController.getTestMaxPoints(test);
        System.out.println("Your result - " + studentResult + " out of " + maxTestPoints);
        double percentage = testController.getRatio(student, test);
        System.out.println("You get " + percentage * 100 + "% \n");
        if (testController.isTestPassed(student, test)) {
            System.out.println("You pass the test " + test.getName() + "\n");
        } else {
            System.out.println("You dont pass the test " + test.getName() + " \n");
        }
        printQuestionsStats(student, test);
    }

    private static void printQuestionsStats(Student student, Test test) {
        List<TestQuestion> questions = new TestController().getTestQuestions(test);
        for (TestQuestion question : questions) {
            List<QuestionAnswer> answers = new TestQuestionController().getQuestionAnswers(question);
            System.out.println("For question \"" + question.getQuestionText() + "\" correct answers were:");
            for (QuestionAnswer answer : answers) {
                if (answer.getPoints() > 0) {
                    System.out.println("\"" + answer.getText() + "\"" + " and give " + answer.getPoints() + " points\n");
                }
            }
            System.out.print("Your answers were ");
            TestController testController = new TestController();
            List<QuestionAnswer> studAnswers = testController.getStudentAnswersForQuestion(question, student);
            int pointsForStudAnswer = testController.getPointsForStudentAnswerForQuestion(studAnswers);
            for (QuestionAnswer studAnswer : studAnswers) {
                System.out.println(studAnswer.getText());
            }
            System.out.println("And this answer get you " + pointsForStudAnswer + " points\n");
        }
    }

    private static void showTestsCollectionByPage(Student student) {
        int sort = getTestsSortingOption();
        printTestsWithPage(PAGE_SIZE, 0, student, sort);
    }

    private static void printTestsWithPage(int limit, int offset, Student student, int usersSortingOption) throws IncorrectPageException {
        List<Test> tests = new StudentController().getStudentTests(limit, offset, student, usersSortingOption);
        System.out.println("Tests assigned to student " + student.getLastName() + " " + student.getFirstName() + " are:");
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
                    printTestsWithPage(limit, offset - limit, student, usersSortingOption);
                } catch (IncorrectPageException e) {
                    System.out.println("Cant back to previous page\n");
                    printTestsWithPage(limit, offset, student, usersSortingOption);
                }
                break;
            case NextPage:
                try {
                    printTestsWithPage(limit, offset + limit, student, usersSortingOption);
                } catch (IncorrectPageException e) {
                    System.out.println("Cant go to next page\n");
                    printTestsWithPage(limit, offset, student, usersSortingOption);
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

}

