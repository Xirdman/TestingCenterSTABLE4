package com.testingcenter.controller;

import com.testingcenter.controller.exceptions.GroupNotFoundException;
import com.testingcenter.controller.exceptions.IncorrectPageException;
import com.testingcenter.model.Group;
import com.testingcenter.model.Student;
import com.testingcenter.model.User;
import com.testingcenter.model.sortingoptions.GroupSortingOption;

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
     * Method to get page of Students in group by page
     *
     * @param limit   number of students in collection
     * @param offset  offset from start
     * @param groupId identifier of group
     * @return page of Students in group by page
     */
    public List<Student> getGroupRtings(int limit, int offset, int groupId) {
        List<Student> students = getGroupRatings(groupId).stream().skip(offset).limit(limit).collect(Collectors.toList());
        if (students.isEmpty())
            throw new IncorrectPageException("Cant form a page");
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

    public List<Group> getGroupsSorted(int limit, int offset, GroupSortingOption groupSortingOption) {
        List<Group> groups = new GroupSorter().sortGroups(getGroups(), groupSortingOption).subList(offset, limit);
        if (groups.isEmpty())
            throw new IncorrectPageException("No such page");
        return groups;
    }
}

