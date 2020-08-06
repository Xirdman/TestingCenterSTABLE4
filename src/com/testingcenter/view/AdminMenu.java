package com.testingcenter.view;

import com.testingcenter.controller.AdminController;
import com.testingcenter.model.Admin;
import com.testingcenter.model.User;

import java.util.List;
import java.util.Scanner;

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
    public void showFirstScreen(Admin admin) {
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
        } catch (Exception e) {
            System.out.print("Waiting to choose an option from 1 to 4 \n");
            showFirstScreen(admin);
        }
        switch (i) {
            case 1:
                showUsersList();
                showFirstScreen(admin);
                break;
            case 2:
                printSearchMenu(admin);
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

    private void showUsersList() {
        List<User> users = new AdminController().getUsers();
        for (User user : users) {
            System.out.println(user.getFirstName() + " " + user.getLastName() + " " + user.getClass().getSimpleName());
        }
        System.out.println();
    }


}
