package com.testingcenter.view;

import com.testingcenter.Main;
import com.testingcenter.controller.StudentContoller;
import com.testingcenter.controller.TestController;
import com.testingcenter.controller.TestQuestionController;
import com.testingcenter.model.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


/**
 * View class for admin menu
 *
 * @author Matveev Alexander
 */
public class StudentMenu {

    /**
     * First Screen of student menu after log in
     *
     * @param student Student to display menu after log in
     */
    public static void showFirstScreen(Student student) {
        System.out.print("Choose options you want to do \n");
        System.out.print("1 - Watch your tests and how many questions in it \n");
        System.out.println("2 - To complete test");
        System.out.print("3 - Log out \n");
        System.out.print("4 - Exit \n");
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        try {
            i = Integer.parseInt(scanner.next());
            System.out.print("\n");
        } catch (Exception e) {
            System.out.print("Waiting to choose an option from 1 to 4 \n");
        }
        switch (i) {
            case 1:
                printTests(student);
                showFirstScreen(student);
                break;
            case 2:
                startCompleteTest(student);
                break;
            case 3:
                Main.showWelcomeScreen();
                break;
            case 4:
                scanner.close();
                System.exit(0);
                break;
            default:
                System.out.print("Waiting to choose an option from 1 to 3 \n");
                showFirstScreen(student);
        }
    }

    private static void printTests(Student student) {
        List<Test> studentTests = new StudentContoller().getStudentTests(student);
        if (studentTests.isEmpty())
            System.out.println("You dont have any tests to complete\n");
        else
            for (Test test : studentTests) {
                System.out.print(test.getName() + " " + new TestController().getQuestionNumber(test) + "\n");
            }
    }

    private static void startCompleteTest(Student student) {
        List<Test> list = new StudentContoller().getStudentTests(student);
        if (list.isEmpty())
            System.out.println("You dont have any tests to complete\n");
        else
            chooseTest(student, list);
        showFirstScreen(student);
    }

    private static void chooseTest(Student student, List<Test> studentUncompletedTests) {
        System.out.println("Choose test to complete");
        int i = 1;
        for (Test test : studentUncompletedTests) {
            System.out.println(i + " - to complete " + test.getName());
            i++;
        }
        Scanner scanner = new Scanner(System.in);
        int choosenTestPosition = -1;
        try {
            choosenTestPosition = Integer.parseInt(scanner.next());
        } catch (Exception e) {
            System.out.println("Waiting for your choice from 1 to " + i + " or 0 to go to student screen");
        }
        if (choosenTestPosition > 0) {
            completeTest(student, studentUncompletedTests.get(choosenTestPosition - 1));
        }
    }

    private static void completeTest(Student student, Test test) {
        if (new TestController()
                .getTestQuestions(test)
                .stream().allMatch(p -> printAnswersForQuestion(p, student))) {
            System.out.println("\nCongratulations! You answer to all questions from " + test.getName() + "\n");
            Assignment assignment = new StudentContoller().getAssignmentForStudentsTest(student, test);
            assignment.setIsCompleted(true);
            printResultsAfterTest(assignment);
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


}
