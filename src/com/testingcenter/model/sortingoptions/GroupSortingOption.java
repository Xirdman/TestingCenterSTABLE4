package com.testingcenter.model.sortingoptions;
/**
 * Enum for possible options to sort collections of groups
 */
public enum GroupSortingOption {
    //1- to sort by identifier, 2 - to sort by name, 3- to sort by group capaci
    SortGroupsByIdentifier("Sort groups by identifier"),
    SortGroupsByName("Sort groups by name"),
    SortGroupsByCapacity("Sort groups by capacity");
    private String text;

    /**
     * Constructor of class
     * @param text text of sorting
     */
    GroupSortingOption(String text) {
        this.text = text;
    }

    /**
     * Method to get text of sorting
     * @return text of sorting
     */
    public String getText() {
        return text;
    }
}
