package com.testingcenter.model;

/**
 * Class representing student group
 *
 * @author Matveev Alexander
 */
public class Group {
    private String name;
    private int id;

    /**
     * Constructor for exemplar of group
     *
     * @param name name of the group
     * @param id   identificator of group
     */
    public Group(String name, int id) {
        this.name = name;
        this.id = id;
    }

    /**
     * Mathod to get Group name
     *
     * @return group name
     */
    public String getName() {
        return name;
    }

    /**
     * Method to get identificator of group
     *
     * @return identificator of group
     */
    public int getId() {
        return id;
    }
}
