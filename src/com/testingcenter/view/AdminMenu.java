package com.testingcenter.view;

import com.testingcenter.controller.UsersSearchController;
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
        int sortOpt = getUsersSortingOption();
        printPageOfUsers(PAGE_SIZE, 0, sortOpt);
    }

    private static void printPageOfUsers(int limit, int offset, int sortingOptions) {
        List<User> users = new UsersSearchController(sortingOptions).searchByFirstName(limit, offset, "");
        System.out.println("Users :");
        users.forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName() + " " + a.getMiddleName()));
        System.out.println("page - " + ((offset / limit) + 1) + "\n");
        PagingOptions pagingOption;
        if (offset == 0)
            pagingOption = getPagingOptionWithoutPrevPageOption();
        else pagingOption = getPagingOption();
        switch (pagingOption) {
            case Exit:
                exit();
                break;
            case LogOut:
                logOut();
                break;
            case NextPage:
                try {
                    printPageOfUsers(limit, offset + limit, sortingOptions);
                } catch (IncorrectPageException e) {
                    System.out.println("There is no next page");
                    printPageOfUsers(limit, offset, sortingOptions);
                }
                break;
            case PrevPage:
                try {
                    printPageOfUsers(limit, offset - limit, sortingOptions);
                } catch (IncorrectPageException e) {
                    System.out.println("There is no previous page");
                    printPageOfUsers(limit, offset, sortingOptions);
                }
                break;
            default:
                break;
        }
    }

}
