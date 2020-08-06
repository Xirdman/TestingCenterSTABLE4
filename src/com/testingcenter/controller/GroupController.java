package com.testingcenter.controller;

import com.testingcenter.model.Group;
import com.testingcenter.model.Student;
import com.testingcenter.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to make business logic with Student user
 *
 * @author Matveev Alexander
 */
public class GroupController {
    /**
     * Method to get all students by group
     *
     * @param groupId identifier of group
     * @return Collection of students
     */
    public List<Student> getStudentsByGroup(int groupId) {
        List<Student> studentsList = new ArrayList<>();
        List<User> list = Repository.getUsers();
        for (User user : list)
            if (user instanceof Student)
                if (((Student) user).getGroupId() == groupId)
                    studentsList.add((Student) user);
        return studentsList;
    }

    /**
     * Method to get all rating of students in group
     *
     * @param groupId identifier number of group
     * @return List of students sorted descend by rating
     */
    public List<Student> getGroupRatings(int groupId) {
        List<Student> students = new GroupController().getStudentsByGroup(groupId);
        StudentContoller studentContoller = new StudentContoller();
        for (Student student : students)
            studentContoller.setRating(student);
        students.sort((a, b) -> Double.compare(b.getRating(), a.getRating()));
        return students;
    }

    /**
     * Method to check if group with such group id exists
     *
     * @param groupId group identifier to check exist or not
     * @return true if group exist false if not
     */
    public boolean isGroupExists(int groupId) {
        List<Group> groups = Repository.getGroups();
        for (Group group : groups)
            if (group.getId() == groupId) {
                return true;
            }
        return false;
    }

    /**
     * Method to get groups
     *
     * @return List of Groups
     */
    public List<Group> getGroups() {
        return Repository.getGroups();
    }
}
