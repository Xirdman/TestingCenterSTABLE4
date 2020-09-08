package com.testingcenter.view.searchviews;

import com.testingcenter.controller.SearchController;
import com.testingcenter.controller.exceptions.IncorrectPageException;
import com.testingcenter.model.Admin;
import com.testingcenter.view.UserMenu;

import java.util.List;

/**
 * Class to print menu to search in admins
 *
 * @author Matveev Alexander
 */
public class SearchInAdminsView extends UserMenu {
    /**
     * Method to print menu to search in admins
     */
    public static void printSearchInAdmins() {
        System.out.print("Choose option you want to do \n");
        System.out.print("1 - Search by Admin first name \n");
        System.out.print("2 - Search by Admin last name \n");
        System.out.print("3 - Go to previous menu \n");
        System.out.print("4 - Log out \n");
        System.out.print("5 - Exit \n");
        int i = 0;
        try {
            i = getIntFromKeyboard();
            System.out.print("\n");
        } catch (Exception e) {
            System.out.print("Waiting to choose an option from 1 to 5 \n");
        }
        switch (i) {
            case 1:
                searchByAdminFirstName();
                break;
            case 2:
                searchByAdminLastName();
                break;
            case 3:
                SearchInUsersView.printSearchInUsers();
                break;
            case 4:
                logOut();
                break;
            case 5:
                exit();
                break;
            default:
                System.out.print("Waiting to choose an option from 1 to 5 \n");
                printSearchInAdmins();
        }
    }

    private static void searchByAdminFirstName() {
        System.out.print("Enter admin first name:");
        String adminFirstName;
        try {
            adminFirstName = getStringFromKeyboard();
            SearchController searchController = new SearchController();
            List<List<Admin>> admins = searchController.searchAdminsByAdminFirstNameWithPages(PAGE_SIZE, adminFirstName);
            if (admins.get(0).isEmpty())
                System.out.println("Admin with first name " + adminFirstName + " not found");
            else {
                System.out.println("Admin with first name " + adminFirstName + "  found:");
                printAdminsListByPage(0, admins);
            }
        } catch (Exception e) {
            System.out.print("Invalid input");
            searchByAdminFirstName();
        }
    }

    private static void searchByAdminLastName() {
        System.out.print("Enter admin last name:");
        String adminLastName;
        try {
            adminLastName = getStringFromKeyboard();
            SearchController searchController = new SearchController();
            List<List<Admin>> admins = searchController.searchAdminsByAdminLastNameWithPages(PAGE_SIZE, adminLastName);
            if (admins.get(0).isEmpty())
                System.out.println("Admin with last name " + adminLastName + " not found");
            else {
                System.out.println("Admin with last name " + adminLastName + "  found:");
                printAdminsListByPage(0, admins);
            }
        } catch (Exception e) {
            System.out.print("Invalid input");
            searchByAdminLastName();
        }
    }

    private static void printAdminsListByPage(int page, List<List<Admin>> admins) {
        if (page >= admins.size() || page < 0)
            throw new IncorrectPageException("Page " + (page + 1) + " not found");
        System.out.println("Admins page number " + (page + 1) + ":");
        List<Admin> userPageToPrint = admins.get(page);
        for (Admin admin : userPageToPrint) {
            System.out.println("Admin first name - " + admin.getFirstName() + " and last name - " + admin.getLastName());
        }
        System.out.println();
        choosePageOfAdminsCollectionToPrint(page, admins);
    }

    private static void choosePageOfAdminsCollectionToPrint(int page, List<List<Admin>> admins) {
        PagingOptions chosenOption;
        if (page == 0) {
            if (page == admins.size() - 1) {
                System.out.println("It is the only page\n");
                chosenOption = PagingOptions.Back;
            } else {
                chosenOption = chooseOptionWithoutPrevPage();
            }
        } else {
            if (page == admins.size() - 1) {
                chosenOption = chooseOptionWithoutNextPage();
            } else {
                chosenOption = choosePagingOption();
            }
        }
        switch (chosenOption) {
            case PrevPage:
                printAdminsListByPage(page - 1, admins);
                break;
            case NextPage:
                printAdminsListByPage(page + 1, admins);
                break;
            case Back:
                break;
            case LogOut:
                logOut();
                break;
            case Exit:
                exit();
                break;
            default:
                choosePageOfAdminsCollectionToPrint(page, admins);
        }
    }
}
