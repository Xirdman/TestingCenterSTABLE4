package com.testingcenter.controller;

import com.testingcenter.controller.exceptions.IncorrectPageException;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to divide collection into pages
 *
 * @author Matveev Alexander
 */
public class PageConverter {
    /**
     * Method to divide collection into pages
     *
     * @param pageSize size of the page
     * @param list     list to divide into pages
     * @param <T>      type of data in collection
     * @return collection divided into pages
     */
    protected <T> List<List<T>> convert(int pageSize, List<T> list) {
        if (pageSize < 1)
            throw new IncorrectPageException("Page size is to low");
        List<List<T>> result = new ArrayList<>();
        int counter = 0;
        List<T> page = new ArrayList<>();
        for (T element : list) {
            if (counter < pageSize) {
                page.add(element);
                counter++;
            } else {
                result.add(page);
                page = new ArrayList<>();
                page.add(element);
                counter = 1;
            }
        }
        result.add(page);
        return result;
    }
}
