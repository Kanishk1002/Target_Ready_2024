package com.targetindia.programs;

import com.targetindia.exceptions.*;
import com.targetindia.model.CRUDOperator;
import com.targetindia.utils.KeyboardUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class Main {
    private static final String ADD_CUSTOMER_COMMAND = "1";
    private static final String VIEW_ALL_CUSTOMERS_COMMAND = "2";
    private static final String SEARCH_CUSTOMER_COMMAND = "3";
    private static final String DELETE_CUSTOMER_COMMAND = "4";
    private static final String UPDATE_CUSTOMER_COMMAND = "5";
    private static final String EXIT_COMMAND = "6";
    private static final String MENU_OPTIONS = """

            MENU
            1. Add new customer
            2. View all customers
            3. Search customers by city
            4. Delete a customer (by ID)
            5. Search customers by ID and edit/update details
            6. Exit
            
            Enter the choice:\s""";

    public static void main(String[] args) {
        String input = KeyboardUtil.getString(MENU_OPTIONS);

        while(!input.equals(EXIT_COMMAND)) {

            try {
                switch(input) {

                    case ADD_CUSTOMER_COMMAND:
                        CRUDOperator.processAddData();
                        break;

                    case VIEW_ALL_CUSTOMERS_COMMAND:
                        CRUDOperator.printData();
                        break;

                    case SEARCH_CUSTOMER_COMMAND:
                        CRUDOperator.processSearchData();
                        break;

                    case DELETE_CUSTOMER_COMMAND:
                        CRUDOperator.processDeleteData();
                        break;

                    case UPDATE_CUSTOMER_COMMAND:
                        CRUDOperator.processUpdateData();
                        break;

                    default:
                        System.out.println("Please enter a valid input or enter \"exit\" to terminate the program.\n");
                        break;
                }
            } catch (InputFormatException |
                     InvalidIdException |
                     InvalidNumberException |
                     InvalidEmailException |
                     InvalidCityException |
                     InvalidNameException |
                     IOException e) {
                System.out.println("\n" + e.getMessage());
            }

            input = KeyboardUtil.getString(MENU_OPTIONS);
        }
    }
}