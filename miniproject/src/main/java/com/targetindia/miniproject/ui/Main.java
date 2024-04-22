package com.targetindia.miniproject.ui;

import com.targetindia.miniproject.service.CustomerManager;
import com.targetindia.miniproject.service.ServiceException;
import com.targetindia.miniproject.utils.KeyboardUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.InputMismatchException;

@Slf4j
public class Main {

    CustomerManager cm;

    public static void main(String[] args) {
        log.trace("starting app...");
        new Main().start();
        log.trace("terminating app.");
    }

    public Main() {
        try {
            cm = new CustomerManager();
        } catch (ServiceException e) {
            log.warn("error while creating customer manager", e);
            System.out.println("There was an error! Please check logs.");
            System.exit(1);
        }
    }

    int menu() {
        System.out.println("========= Main Menu =========");
        System.out.println("0. Exit");
        System.out.println("1. List all customers");
        System.out.println("2. Add a new customer");
        System.out.println("3. Search customer by ID");
        System.out.println("4. Search customer by Email");
        System.out.println("5. Search customer by Phone");
        System.out.println("6. Search customers by City");
        System.out.println("7. Update customer by ID");
        System.out.println("8. Delete customer by ID");

        try {
            return KeyboardUtil.getInt("Please enter a choice: ");
        } catch (InputMismatchException e) {
            log.warn("there was error while accepting menu choice", e);
            return -1;
        }
    }

    void start() {
        int choice;

        while((choice = menu()) != 0) {
            log.trace("user made a choice {}", choice);

            switch (choice) {
                case 1:
                    CustomerActions.displayAllCustomers(cm);
                    break;
                case 2:
                    CustomerActions.addNewCustomer(cm);
                    break;
                case 3:
                    CustomerActions.displayCustomerByID(cm);
                    break;
                case 4:
                    CustomerActions.displayCustomerByEmail(cm);
                    break;
                case 5:
                    CustomerActions.displayCustomerByPhone(cm);
                    break;
                case 6:
                    CustomerActions.displayCustomerByCity(cm);
                    break;
                case 7:
                    CustomerActions.updateCustomerByID(cm);
                    break;
                case 8:
                    CustomerActions.deleteCustomerByID(cm);
                    break;
                default:
                    System.out.println("Invalid user choice. Please enter again.");
            }
        }
    }
}
