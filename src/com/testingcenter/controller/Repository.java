package com.testingcenter.controller;

import com.testingcenter.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for data
 *
 * @author Matveev Alexander
 */
public class Repository {

    private static List<User> users;
    private static List<Test> tests;
    private static List<Assignment> assignments;
    private static List<TestQuestion> questions;
    private static List<Group> groups;
    private static List<TestResult> results;
    private static List<QuestionAnswer> questionAnswers;
    private static List<StudentAnswerForTestQuestion> studentAnswers;

    /**
     * Method to get all student answers for questions of test
     *
     * @return Collection of answers for questions
     */
    public static List<StudentAnswerForTestQuestion> getStudentAnswers() {
        return studentAnswers;
    }

    /**
     * Method to get all question answers from repository
     *
     * @return Collection of question answers
     */
    public static List<QuestionAnswer> getQuestionAnswers() {
        return questionAnswers;
    }

    /**
     * Method to get all results from repository
     *
     * @return Collection of results
     */
    public static List<TestResult> getResults() {
        return results;
    }

    /**
     * Method to initialize data of users, tests,questions and assignments.Later it will upload data from in memory database
     */
    public static void initialize() {
        users = new ArrayList<>();
        Teacher teacher1 = new Teacher("Dmitriy", "Bykovets", "login", "password");
        Teacher teacher2 = new Teacher("Arnold", "Shcwarzeneger", "kabuto", "perpeto");
        users.add(teacher1);
        users.add(teacher2);

        Student student1 = new Student("Ivan", "Ivanov", "ivan", "123");
        Student student2 = new Student("Sergey", "Sergeevich", "Sergey", "qwerty");
        users.add(student1);
        users.add(student2);
        users.add(new Admin("Vladimir", "Putin", "admin", "admin"));

        tests = new ArrayList<>();
        Test test1 = new Test("JavaSE", teacher1);
        Test test2 = new Test("JavaEE", teacher1);
        Test test99 = new Test("Paging test Test 1", teacher1);
        Test test298 = new Test("Paging test Test 2", teacher1);
        Test test3 = new Test("Bodybuilding", teacher2);
        tests.add(test1);
        tests.add(test2);
        tests.add(test3);
        tests.add(test99);
        tests.add(test298);
        Assignment assignment99 = new Assignment(student1, test99);
        Assignment assignment298 = new Assignment(student1, test298);


        Assignment assignment1 = new Assignment(student1, test1);
        Assignment assignment2 = new Assignment(student2, test2, true);
        Assignment assignment3 = new Assignment(student1, test2);
        Assignment assignment4 = new Assignment(student1, test3);
        assignments = new ArrayList<>();
        assignments.add(assignment1);
        assignments.add(assignment2);
        assignments.add(assignment3);
        assignments.add(assignment4);
        assignments.add(assignment99);
        assignments.add(assignment298);

        questions = new ArrayList<>();
        questionAnswers = new ArrayList<>();
        int testQuestionIdCounter = 0;
        TestQuestion question1 = new TestQuestion("How many primitive data types in Java?", test1, testQuestionIdCounter++);
        int answerId = 0;
        //Negative points just to prevent situations where student can choose all the answers for question and get points
        questionAnswers.add(new QuestionAnswer("6", -2, question1, answerId++));
        questionAnswers.add(new QuestionAnswer("7", -2, question1, answerId++));
        questionAnswers.add(new QuestionAnswer("8", 3, question1, answerId++));
        questions.add(question1);

        TestQuestion question2 = new TestQuestion("Will you go to gym tomorrow?", test3, testQuestionIdCounter++);
        questionAnswers.add(new QuestionAnswer("Yes", 2, question2, answerId++));
        questionAnswers.add(new QuestionAnswer("No", -2, question2, answerId++));
        questions.add(question2);

        TestQuestion question3 = new TestQuestion("How to print in console?", test1, testQuestionIdCounter++);
        questionAnswers.add(new QuestionAnswer("System.out.print()", 3, question3, answerId++));
        questionAnswers.add(new QuestionAnswer("printf()", -2, question3, answerId++));
        questionAnswers.add(new QuestionAnswer("cout>>", -2, question3, answerId++));
        questions.add(question3);

        TestQuestion question4 = new TestQuestion("Can you extend java.lang.String?", test1, testQuestionIdCounter++);
        questionAnswers.add(new QuestionAnswer("yes", -2, question4, answerId++));
        questionAnswers.add(new QuestionAnswer("no", 2, question4, answerId++));
        questions.add(question4);

        TestQuestion question5 = new TestQuestion("Which java.util.Set realisation sort elements by comparator or in natural order?", test1, testQuestionIdCounter++);
        questionAnswers.add(new QuestionAnswer("java.util.TreeSet", 3, question5, answerId++));
        questionAnswers.add(new QuestionAnswer("java.util.HashSet", -2, question5, answerId++));
        questionAnswers.add(new QuestionAnswer("java.util.LinkedHashSet", -2, question5, answerId++));
        questions.add(question5);

        TestQuestion question6 = new TestQuestion("Which methods have interface HttpServletResponse?", test2, testQuestionIdCounter++);
        questionAnswers.add(new QuestionAnswer("sendError", 5, question6, answerId++));
        questionAnswers.add(new QuestionAnswer("sendStatus", -5, question6, answerId++));
        questionAnswers.add(new QuestionAnswer("sendRedirect", 5, question6, answerId++));
        questionAnswers.add(new QuestionAnswer("sendHeader", -5, question6, answerId++));
        questionAnswers.add(new QuestionAnswer("sendWarning", -5, question6, answerId++));
        questions.add(question6);

        TestQuestion question7 = new TestQuestion("Which class/interface extends/implements HttpServlet", test2, testQuestionIdCounter++);
        questionAnswers.add(new QuestionAnswer("Servlet", -3, question7, answerId++));
        questionAnswers.add(new QuestionAnswer("Http", -3, question7, answerId++));
        questionAnswers.add(new QuestionAnswer("GenericServlet", 3, question7, answerId++));
        questionAnswers.add(new QuestionAnswer("HttpServletImpl", -3, question7, answerId++));
        questionAnswers.add(new QuestionAnswer("AbstractServlet", -3, question7, answerId++));
        questions.add(question7);

        int groupIdCounter = 0;
        groups = new ArrayList<>();
        Group group1 = new Group("Group 420", groupIdCounter++);
        Group group2 = new Group("Group 322", groupIdCounter++);
        groups.add(group1);
        groups.add(group2);
        Group group3 = new Group("Group 101", groupIdCounter++);
        groups.add(group3);
        Group group4 = new Group("Group 444", groupIdCounter++);
        groups.add(group4);
        Group group5 = new Group("Group 502", groupIdCounter++);
        groups.add(group5);

        student1.setGroup(0);
        student2.setGroup(0);
        Student student3 = new Student("Petr", "Petrov", "petya", "petro");
        Student student4 = new Student("Victor", "Nurgaliev", "vic", "Victory");
        users.add(student3);
        users.add(student4);
        student3.setGroup(1);
        student4.setGroup(1);
        Student student5 = new Student("Andrey", "Andreev", "andrew", "andy");
        users.add(student5);
        student5.setGroup(2);
        Student student6 = new Student("Boris", "Bogolepov", "boar", "brand");
        users.add(student6);
        student6.setGroup(3);
        Student student7 = new Student("Veniamin", "Volochkov", "vrun", "vvv");
        users.add(student7);
        student7.setGroup(4);
        Student student8 = new Student("Gleb", "Gaidarov", "gusli", "gusi");
        users.add(student8);
        student8.setGroup(2);
        Student student9 = new Student("Denis", "Durov", "draka", "ded");
        users.add(student9);
        student9.setGroup(3);
        Student student10 = new Student("Evlampiy", "Egorov", "esqire", "elka");
        users.add(student10);
        student5.setGroup(4);
        Student student11 = new Student("Georgiy", "Golubev", "granata", "gandhi");
        users.add(student11);
        student5.setGroup(2);


        TestResult testResult1 = new TestResult(student1, test1, 4);
        TestResult testResult2 = new TestResult(student2, test1, 3);
        TestResult testResult3 = new TestResult(student3, test1, 5);
        TestResult testResult4 = new TestResult(student4, test1, 1);
        results = new ArrayList<>();
        results.add(testResult1);
        results.add(testResult2);
        results.add(testResult3);
        results.add(testResult4);

        studentAnswers = new ArrayList<>();
    }

    /**
     * Get collection of users
     *
     * @return List of users
     */
    public static List<User> getUsers() {
        return users;
    }

    /**
     * Get collection of tests
     *
     * @return List of tests
     */
    public static List<Test> getTests() {
        return tests;
    }

    /**
     * Get collection of assignments
     *
     * @return List of assignments
     */
    public static List<Assignment> getAssignments() {
        return assignments;
    }

    /**
     * Get collection of test questions
     *
     * @return List of test questions
     */
    public static List<TestQuestion> getQuestions() {
        return questions;
    }

    /**
     * Method to get collection of groups
     *
     * @return List of groups
     */
    public static List<Group> getGroups() {
        return groups;
    }
}

