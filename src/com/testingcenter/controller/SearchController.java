package com.testingcenter.controller;

import com.testingcenter.model.Student;
import com.testingcenter.model.Test;
import com.testingcenter.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchController {
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
        List<User> students = Repository.getUsers();
        return students.stream()
                .filter(e -> e instanceof Student)
                .filter(e -> e.getFirstName().equals(studentFirstName))
                .map(s->(Student)s)
                .collect(Collectors.toList());

    }
    public List<Student> searchByStudentLastName(String studentLastName) {
        List<User> students = Repository.getUsers();
        return students.stream()
                .filter(e -> e instanceof Student)
                .filter(e -> e.getLastName().equals(studentLastName))
                .map(s->(Student)s)
                .collect(Collectors.toList());

    }
}
