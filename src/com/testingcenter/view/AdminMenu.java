package com.testingcenter.view;

import com.testingcenter.controller.AdminController;
import com.testingcenter.controller.exceptions.IncorrectInputException;
import com.testingcenter.controller.exceptions.IncorrectPageException;
import com.testingcenter.model.Admin;
import com.testingcenter.model.User;

import java.util.List;

/**
 * View class for admin menu
 *
 * @author Matveev Alexander
 */
public class AdminMenu extends UserMenu {
    /**
     * First Screen of admin menu after log in
     *
     * @param admin Admin to display menu
     */
    public static void showFirstScreen(Admin admin) {
        System.out.println("Hello " + admin.getFirstName() + " " + admin.getLastName());
        System.out.print("Choose options you want to do \n");
        System.out.print("1 - Watch users list \n");
        System.out.print("2 - To search \n");
        System.out.print("3 - Log out \n");
        System.out.print("4 - Exit \n");
        int i = 0;
        try {
            i = getIntFromKeyboard();
            System.out.print("\n");
        } catch (IncorrectInputException e) {
            System.out.print("Error: \n");
            System.out.print(e.getMessage());
            showFirstScreen(admin);
        }
        switch (i) {
            case 1:
                showUsersListByPage();
                showFirstScreen(admin);
                break;
            case 2:
                printSearchMenu();
                showFirstScreen(admin);
                break;
            case 3:
                logOut();
                break;
            case 4:
                exit();
                break;
            default:
                System.out.print("Waiting to choose an option from 1 to 4 \n");
                showFirstScreen(admin);
        }
    }

    private static void showUsersListByPage() {
        List<List<User>> usersPages = new AdminController().getUsersByPageSize(PAGE_SIZE);
        int page = 0;
        try {
            printUsersListByPage(page, usersPages);
        } catch (IncorrectPageException e) {
            System.out.print("Error: \n");
            System.out.print(e.getMessage() + "\n");
        }
    }

    private static void printUsersListByPage(int page, List<List<User>> users) {
        if (page >= users.size() || page < 0)
            throw new IncorrectPageException("Page " + (page + 1) + " not found");
        System.out.println("Users page number " + page + ":");
        List<User> userPageToPrint = users.get(page);
        for (User user : userPageToPrint) {
            System.out.println(user.getFirstName() + " " + user.getLastName() + " " + user.getClass().getSimpleName());
        }
        System.out.println();
        choosePageOfUsersCollectionToPrint(page, users);
    }

    private static void choosePageOfUsersCollectionToPrint(int page, List<List<User>> users) {
        PagingOptions chosenOption;
        if (page == 0) {
            if (page == users.size() - 1) {
                System.out.println("It is the only page\n");
                chosenOption = PagingOptions.Back;
            } else {
                chosenOption = chooseOptionWithoutPrevPage();
            }
        } else {
            if (page == users.size() - 1) {
                chosenOption = chooseOptionWithoutNextPage();
            } else {
                chosenOption = choosePagingOption();
            }
        }
        switch (chosenOption) {
            case PrevPage:
                printUsersListByPage(page - 1, users);
                break;
            case NextPage:
                printUsersListByPage(page + 1, users);
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
                choosePageOfUsersCollectionToPrint(page, users);
        }
    }


}
