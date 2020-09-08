package com.testingcenter.view.searchviews;

import com.testingcenter.view.UserMenu;

/**
 * Class to print menu to search in students
 *
 * @author Matveev Alexander
 */
public class SearchInUsersView extends UserMenu {
    /**
     * Method to print menu to search in students
     */
    public static void printSearchInUsers() {
        System.out.print("Choose option you want to do \n");
        System.out.print("1 - Search in students \n");
        System.out.print("2 - Search in admins \n");
        System.out.print("3 - Search in teachers \n");
        System.out.print("4 - Go to previous menu \n");
        System.out.print("5 - Log out \n");
        System.out.print("6 - Exit \n");
        int i = 0;
        try {
            i = getIntFromKeyboard();
            System.out.print("\n");
        } catch (Exception e) {
            System.out.print("Waiting to choose an option from 1 to 6 \n");
        }
        switch (i) {
            case 1:
                SearchInStudentsView.printSearchInStudents();
                break;
            case 2:
                SearchInAdminsView.printSearchInAdmins();
                break;
            case 3:
                SearchInTeachersView.printSearchInTeachers();
                break;
            case 4:
                break;
            case 5:
                logOut();
                break;
            case 6:
                exit();
                break;
            default:
                System.out.print("Waiting to choose an option from 1 to 6 \n");
                printSearchInUsers();
        }
    }

}
