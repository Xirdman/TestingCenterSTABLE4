package com.testingcenter.controller;

import com.testingcenter.controller.exceptions.GroupNotFoundException;
import com.testingcenter.model.Group;
import com.testingcenter.model.Student;
import com.testingcenter.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        StudentController studentContoller = new StudentController();
        for (Student student : students)
            studentContoller.setRating(student);
        students.sort((a, b) -> Double.compare(b.getRating(), a.getRating()));
        return students;
    }

    /**
     * Method to check is group with identifier exists
     *
     * @param groupId group identifier
     * @throws GroupNotFoundException throws exception if group with identifier not exist
     */
    public boolean isGroupExists(int groupId) throws GroupNotFoundException {
        List<Group> groups = Repository.getGroups();
        boolean groupExist = false;
        for (Group group : groups)
            if (group.getId() == groupId) {
                groupExist = true;
            }
        if (!groupExist) {
            throw new GroupNotFoundException("Group with id - " + groupId + " not Found");
        }
        return true;
    }

    /**
     * Method to get groups
     *
     * @return List of Groups
     */
    public List<Group> getGroups() {
        return Repository.getGroups();
    }

    /**
     * Method to get all groups divided into pages
     *
     * @param pageSize size of page
     * @return Collection of groups divided into pages sorted by group name
     */
    public List<List<Group>> getGroupsByPageSize(int pageSize) {
        List<Group> groups = getGroups()
                .stream()
                .sorted((a, b) -> a.getName().compareTo(b.getName()))
                .collect(Collectors.toList());
        return new PageConverter().convert(pageSize, groups);
    }

    /**
     * Method to get all students from group divided into pages
     *
     * @param pageSize size of the page
     * @param groupId  identifier of group
     * @return Collection of students from group divided into pages and sorted by last name
     */
    public List<List<Student>> getStudentsByGroupByPageSize(int pageSize, int groupId) {
        List<Student> students = getGroupRatings(groupId)
                .stream()
                .sorted((a, b) -> a.getLastName().compareTo(b.getLastName()))
                .collect(Collectors.toList());
        return new PageConverter().convert(pageSize, students);
    }
}

