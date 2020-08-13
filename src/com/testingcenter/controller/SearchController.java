package com.testingcenter.controller;

import com.testingcenter.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchController {
    enum Users{
        ADMIN,
        TEACHER,
        STUDENT
    }
    public List<Test> searchTestsByName(String textInName) {
        List<Test> tests = Repository.getTests();
        return tests.stream().filter(e -> e.getName().contains(textInName)).collect(Collectors.toList());
    }

    public List<Test> searchTestByNumberOfQuestions(int numberOfQuestions) {
        List<Test> tests = Repository.getTests();
        List<Test> result = new ArrayList<>();
        TestController testController = new TestController();
        for (Test test : tests)
            if (testController.getQuestionNumber(test) == numberOfQuestions)
                result.add(test);
        return result;
    }

    public List<Test> searchTestsByTeacherFirstName(String teacherFirstName) {
        List<Test> tests = Repository.getTests();
        return tests.stream().filter(e -> e.getTeacher().getFirstName().equals(teacherFirstName)).collect(Collectors.toList());
    }

    public List<Test> searchTestsByTeacherLastName(String teacherLastName) {
        List<Test> tests = Repository.getTests();
        return tests.stream().filter(e -> e.getTeacher().getLastName().equals(teacherLastName)).collect(Collectors.toList());
    }

    public List<Student> searchByStudentFirstName(String studentFirstName) {
        List<User> users = Repository.getUsers();
        return users.stream()
                .filter(e -> e instanceof Student)
                .filter(e -> e.getFirstName().equals(studentFirstName))
                .map(s->(Student)s)
                .collect(Collectors.toList());

    }
    public List<Student> searchByStudentLastName(String studentLastName) {
        List<User> users = Repository.getUsers();
        return users.stream()
                .filter(e -> e instanceof Student)
                .filter(e -> e.getLastName().equals(studentLastName))
                .map(s->(Student)s)
                .collect(Collectors.toList());

    }
    public List<Admin> searchByAdminFirstName(String adminFirstName) {
        List<User> users = Repository.getUsers();
        return users.stream()
                .filter(e -> e instanceof Admin)
                .filter(e -> e.getFirstName().equals(adminFirstName))
                .map(s->(Admin)s)
                .collect(Collectors.toList());

    }
    public List<Admin> searchByAdminLastName(String adminLastName) {
        List<User> users = Repository.getUsers();
        return users.stream()
                .filter(e -> e instanceof Admin)
                .filter(e -> e.getLastName().equals(adminLastName))
                .map(s->(Admin)s)
                .collect(Collectors.toList());

    }
    public List<Teacher> searchByTeacherFirstName(String teacherFirstName) {
        List<User> users = Repository.getUsers();
        return users.stream()
                .filter(e -> e instanceof Teacher)
                .filter(e -> e.getFirstName().equals(teacherFirstName))
                .map(s->(Teacher)s)
                .collect(Collectors.toList());

    }
    public List<Teacher> searchByTeacherLastName(String teacherLastName) {
        List<User> users = Repository.getUsers();
        return users.stream()
                .filter(e -> e instanceof Teacher)
                .filter(e -> e.getLastName().equals(teacherLastName))
                .map(s->(Teacher)s)
                .collect(Collectors.toList());

    }
    public List<TestQuestion> searchTestQuestionsByWordInText(String searchedWord){
        List<TestQuestion> questions = Repository.getQuestions();
        return questions.stream()
                .filter(e-> e.getQuestionText().contains(searchedWord))
                .collect(Collectors.toList());
    }
}
