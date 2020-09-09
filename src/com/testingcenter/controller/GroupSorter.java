package com.testingcenter.controller;

import com.testingcenter.model.Group;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to sort List of Groups
 */
public class GroupSorter {
    /**
     * Method to sort List of Groups
     *
     * @param groups        groups to sort
     * @param codeOfSorting code of sorting to choose how sort collection. 1- to sort by identifier, 2 - to sort by name, 3- to sort by group capacity
     * @return
     */
    public List<Group> sortGroups(List<Group> groups, int codeOfSorting) {
        switch (codeOfSorting) {
            case 1:
                return groups.stream()
                        .sorted((a, b) -> Integer.compare(a.getId(), b.getId()))
                        .collect(Collectors.toList());
            case 2:
                return groups.stream()
                        .sorted((a, b) -> a.getName().compareTo(b.getName()))
                        .collect(Collectors.toList());
            case 3:
                GroupController groupController = new GroupController();
                return groups.stream()
                        .sorted((a, b) -> Integer.compare(groupController.getStudentsByGroup(a.getId()).size(), groupController.getStudentsByGroup(b.getId()).size()))
                        .collect(Collectors.toList());
        }
        return groups;
    }
}
