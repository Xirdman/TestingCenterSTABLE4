package com.testingcenter.controller;

import com.testingcenter.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to search tests or users
 *
 * @author Matveev Alexander
 */
public class SearchController {
    private List<Test> searchTestsByName(String textInName) {
        List<Test> tests = Repository.getTests();
        return tests.stream().filter(e -> e.getName().contains(textInName)).collect(Collectors.toList());
    }

    /**
     * Method to get all test whose name contains searched word
     * @param pageSize size of the page in returned collection
     * @param textInName searched word
     * @return Collection of test divided into pages and sorted by number of questions
     */
    public List<List<Test>> searchTestByNameWithPages(int pageSize, String textInName) {
        List<Test> tests = searchTestsByName(textInName)
                .stream()
                .sorted((a,b)->Integer.compare(new TestController().getQuestionNumber(b),new TestController().getQuestionNumber(a)))
                .collect(Collectors.toList());
        return new PageConverter().convert(pageSize,tests);
    }

    private List<Test> searchTestByNumberOfQuestions(int numberOfQuestions) {
        List<Test> tests = Repository.getTests();
        List<Test> result = new ArrayList<>();
        TestController testController = new TestController();
        for (Test test : tests)
            if (testController.getQuestionNumber(test) == numberOfQuestions)
                result.add(test);
        return result;
    }

    /**
     * Method to get all tests whose have exact number of questions
     * @param pageSize size of the page in returned collection
     * @param numberOfQuestions Number of questions in test we searching
     * @return Collection of tests divided into pages sorted by test name
     */
    public List<List<Test>> searchTestsByNumberOfQuestionsWithPages(int pageSize, int numberOfQuestions) {
        List<Test> tests = searchTestByNumberOfQuestions(numberOfQuestions)
                .stream()
                .sorted((a,b)->a.getName().compareTo(b.getName()))
                .collect(Collectors.toList());
        return new PageConverter().convert(pageSize, tests);
    }

    private List<Test> searchTestsByTeacherFirstName(String teacherFirstName) {
        List<Test> tests = Repository.getTests();
        return tests.stream().filter(e -> e.getTeacher().getFirstName().equals(teacherFirstName)).collect(Collectors.toList());
    }
    /**
     * Method to get all test by teacher first name
     * @param pageSize size of the page in returned collection
     * @param teacherFirstName Exact first name of teacher whose test we want to get
     * @return Collection of test of teachers with searched first name and divided into pages and sorted by number of questions in test descending
     */
    public List<List<Test>> searchTestsByTeacherFirstNameByPages(int pageSize, String teacherFirstName) {
        return new PageConverter().convert(pageSize,
                searchTestsByTeacherFirstName(teacherFirstName)
                        .stream()
                        .sorted((a,b)->Integer.compare(new TestController().getQuestionNumber(b),new TestController().getQuestionNumber(a)))
                        .collect(Collectors.toList()));
    }

    /**
     * Method to get all test by teacher last name
     * @param pageSize size of the page in returned collection
     * @param teacherLastName Exact last name of teacher whose test we want to get
     * @return Collection of test of teachers with searched last name and divided into pages and sorted by number of questions in test descending
     */
    public List<List<Test>> searchTestsByTeacherLastNameByPage(int pageSize, String teacherLastName) {
        return new PageConverter().convert(pageSize,
                searchTestsByTeacherLastName(teacherLastName)
                        .stream()
                        .sorted((a,b)->Integer.compare(new TestController().getQuestionNumber(b),new TestController().getQuestionNumber(a)))
                        .collect(Collectors.toList()));
    }

    private List<Test> searchTestsByTeacherLastName(String teacherLastName) {
        List<Test> tests = Repository.getTests();
        return tests.stream().filter(e -> e.getTeacher().getLastName().equals(teacherLastName)).collect(Collectors.toList());
    }

    private List<Student> searchStudentsByStudentFirstName(String studentFirstName) {
        List<User> users = Repository.getUsers();
        return users.stream()
                .filter(e -> e instanceof Student)
                .filter(e -> e.getFirstName().equals(studentFirstName))
                .map(s -> (Student) s)
                .collect(Collectors.toList());

    }
    /**
     * Method to search all students whose first name contains searched word
     * @param pageSize size of the page in returned collection
     * @param studentFirstName Part of the first name searched in first name of the student
     * @return Collection of students whose first name contains searched word
     *       and separated into different collections by page size and sorted by last name
     */
    public List<List<Student>> searchStudentsByStudentFirstNameWithPages(int pageSize, String studentFirstName){
        return new PageConverter().convert(pageSize,searchStudentsByStudentFirstName(studentFirstName)
                .stream()
                .sorted((a,b)->a.getLastName().compareTo(b.getLastName()))
                .collect(Collectors.toList()));
    }

    private List<Student> searchByStudentLastName(String studentLastName) {
        List<User> users = Repository.getUsers();
        return users.stream()
                .filter(e -> e instanceof Student)
                .filter(e -> e.getLastName().equals(studentLastName))
                .map(s -> (Student) s)
                .collect(Collectors.toList());

    }
    /**
     * Method to search all students whose last name contains word
     * @param pageSize size of the page in returned collection
     * @param studentLastName Part of the last name searched in last name of the student
     * @return Collection of students whose last name contains searched word
     * and separated into different collections by page size and sorted by first name
     */
    public List<List<Student>> searchStudentsByStudentLastNameWithPages(int pageSize, String studentLastName){
        return new PageConverter().convert(pageSize,searchByStudentLastName(studentLastName)
                .stream()
                .sorted((a,b)->a.getFirstName().compareTo(b.getFirstName()))
                .collect(Collectors.toList()));
    }

    private List<Admin> searchByAdminFirstName(String adminFirstName) {
        List<User> users = Repository.getUsers();
        return users.stream()
                .filter(e -> e instanceof Admin)
                .filter(e -> e.getFirstName().equals(adminFirstName))
                .map(s -> (Admin) s)
                .collect(Collectors.toList());

    }

    /**
     * Method to search all admins whose first name contains searched word
     * @param pageSize size of the page in returned collection
     * @param adminFirstName Part of the first name searched in first name of the admin
     * @return Collection of admins whose first name contains searched word
     *       and separated into different collections by page size and sorted by last name
     */
    public List<List<Admin>> searchAdminsByAdminFirstNameWithPages(int pageSize,String adminFirstName){
        return new PageConverter().convert(pageSize,searchByAdminFirstName(adminFirstName)
                .stream()
                .sorted((a,b)->a.getLastName().compareTo(b.getLastName()))
                .collect(Collectors.toList()));
    }

    private List<Admin> searchByAdminLastName(String adminLastName) {
        List<User> users = Repository.getUsers();
        return users.stream()
                .filter(e -> e instanceof Admin)
                .filter(e -> e.getLastName().equals(adminLastName))
                .map(s -> (Admin) s)
                .collect(Collectors.toList());

    }

    /**
     * Method to search all admins whose last name contains word
     * @param pageSize size of the page in returned collection
     * @param adminLastName Part of the last name searched in last name of the admin
     * @return Collection of admins whose last name contains searched word
     * and separated into different collections by page size and sorted by first name
     */
    public List<List<Admin>> searchAdminsByAdminLastNameWithPages(int pageSize, String adminLastName){
        return new PageConverter().convert(pageSize,searchByAdminLastName(adminLastName)
                .stream()
                .sorted((a,b)->a.getFirstName().compareTo(b.getFirstName()))
                .collect(Collectors.toList()));
    }

    private List<Teacher> searchByTeacherFirstName(String teacherFirstName) {
        List<User> users = Repository.getUsers();
        return users.stream()
                .filter(e -> e instanceof Teacher)
                .filter(e -> e.getFirstName().equals(teacherFirstName))
                .map(s -> (Teacher) s)
                .collect(Collectors.toList());

    }

    /**
     * Method to search all teacher whose first name contains searched word
     * @param pageSize size of the page in returned collection
     * @param teacherFirstName Part of the first name searched in first name of the teacher
     * @return Collection of teachers whose first name contains searched word
     *       and separated into different collections by page size and sorted by last name
     */
    public List<List<Teacher>> searchTeachersByFirstNameWithPages(int pageSize, String teacherFirstName){
        return new PageConverter().convert(pageSize,searchByTeacherFirstName(teacherFirstName)
                .stream()
                .sorted((a,b)->a.getLastName().compareTo(b.getLastName()))
                .collect(Collectors.toList()));
    }

    private List<Teacher> searchByTeacherLastName(String teacherLastName) {
        List<User> users = Repository.getUsers();
        return users.stream()
                .filter(e -> e instanceof Teacher)
                .filter(e -> e.getLastName().equals(teacherLastName))
                .map(s -> (Teacher) s)
                .collect(Collectors.toList());

    }

    /**
     * Method to search all teachers whose last name contains word
     * @param pageSize size of the page in returned collection
     * @param teacherLastName Part of the last name searched in last name of the teacher
     * @return Collection of teachers whose last name contains searched word
     * and separated into different collections by page size and sorted by first name
     */
    public List<List<Teacher>> searchTeachersByTeacherLastNameWithPages(int pageSize, String teacherLastName){
        return new PageConverter().convert(pageSize,searchByTeacherLastName(teacherLastName)
                .stream()
                .sorted((a,b)->a.getFirstName().compareTo(b.getFirstName()))
                .collect(Collectors.toList()));
    }

    private List<TestQuestion> searchTestQuestionsByWordInText(String searchedWord) {
        List<TestQuestion> questions = Repository.getQuestions();
        return questions.stream()
                .filter(e -> e.getQuestionText().contains(searchedWord))
                .collect(Collectors.toList());
    }

    /**
     * Method to get search test questions which contains searched word in test question and having collection for each page
     * @param pageSize size of the page in returned collection
     * @param seachedWord word searched in Test question text
     * @return test question collection divided by pages
     */
    public List<List<TestQuestion>> searchTestQuestionsByWordInTextWithPages(int pageSize,String seachedWord){
        return new PageConverter().convert(pageSize,searchTestQuestionsByWordInText(seachedWord)
                .stream()
                .sorted((a,b)->a.getQuestionText().compareTo(b.getQuestionText()))
                .collect(Collectors.toList()));
    }

    private List<TestQuestion> searchTestQuestionsByWordInAnswers(String searchedWord){
        List<TestQuestion> questions = Repository.getQuestions();
        TestQuestionController testQuestionController = new TestQuestionController();
        List<TestQuestion> result = new ArrayList<>();
        for(TestQuestion testQuestion: questions){
            boolean flag = false;
            List<QuestionAnswer> answers = testQuestionController.getQuestionAnswers(testQuestion);
            for(QuestionAnswer answer: answers){
                if(answer.getText().contains(searchedWord)){
                    flag = true;
                    break;
                }
            }
            if(flag)
                result.add(testQuestion);

        }
        return result;
    }

    /**
     * Method to get search test questions which contains searched word in answers and having collection for each page
     * @param pageSize size of the page in returned collection
     * @param searchedWord word searched in Test question answers
     * @return test question collection divided by pages
     */
    public List<List<TestQuestion>> searchTestQuestionsByWordInAnswersWithPages(int pageSize, String searchedWord){
        return new PageConverter().convert(pageSize,
                searchTestQuestionsByWordInAnswers(searchedWord)
                        .stream()
                        .sorted((a,b)->a.getQuestionText().compareTo(b.getQuestionText()))
                        .collect(Collectors.toList()));
    }
}
