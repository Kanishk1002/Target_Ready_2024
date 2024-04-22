package com.targetindia.miniproject.ui;

import com.targetindia.miniproject.model.Customer;
import com.targetindia.miniproject.model.ModelException;
import com.targetindia.miniproject.service.CustomerManager;
import com.targetindia.miniproject.service.ServiceException;
import com.targetindia.miniproject.utils.KeyboardUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class CustomerActions {

    private static void displayCustomersList(List<Customer> customers) {
        System.out.printf("%4s|%-20s|%-15s|%-25s|%-15s\n", "ID", "Name", "City", "Email", "Phone");
        System.out.println("-----------------------------------------------------------------------------------");
        for(var c: customers){
            System.out.printf("%4d|%-20s|%-15s|%-25s|%-15s\n",
                    c.getId(),
                    c.getName(),
                    c.getCity(),
                    c.getEmail(),
                    c.getPhone());
        }
        System.out.println("-----------------------------------------------------------------------------------");
    }

    private static void displayCustomer(Customer customer) {
        System.out.printf("%4s|%-20s|%-15s|%-25s|%-15s\n", "ID", "Name", "City", "Email", "Phone");
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.printf("%4d|%-20s|%-15s|%-25s|%-15s\n",
                    customer.getId(),
                    customer.getName(),
                    customer.getCity(),
                    customer.getEmail(),
                    customer.getPhone());
        System.out.println("-----------------------------------------------------------------------------------");
    }

    public static void displayAllCustomers(CustomerManager cm) {
        try {
            var customers = cm.getAll();
            displayCustomersList(customers);
        } catch (ServiceException e) {
            System.out.println("Something went wrong. Please check logs.");
            log.warn("there was error while fetching all customers list via cm.getAll()", e);
        }
    }

    public static void addNewCustomer(CustomerManager cm) {
        try {
            System.out.println("Enter new customer details: ");
            String name = KeyboardUtil.getString("Name  : ");
            String city = KeyboardUtil.getString("City  : ");
            String email = KeyboardUtil.getString("Email : ");
            String phone = KeyboardUtil.getString("Phone : ");

            Customer customer = new Customer(null, name, city, email, phone);

            customer = cm.addNewCustomer(customer);
            System.out.printf("Successfully added new customer with ID: %d\n", customer.getId());
        } catch (ServiceException | ModelException e) {
            System.out.println("Something went wrong, couldn't add new customer. Please check logs.");
            log.warn("Error while adding new user", e);
        }
    }

    public static void displayCustomerByID(CustomerManager cm) {
        try {
            int id = KeyboardUtil.getInt("Enter customer ID: ");
            Customer customer = cm.getById(id);
            displayCustomer(customer);
        } catch (ServiceException e) {
            System.out.println("Cannot fetch customer with given ID. Please check logs.");
            log.warn("Error while fetching customer with ID.", e);
        }
    }

    public static void displayCustomerByEmail(CustomerManager cm) {
        try {
            String email = KeyboardUtil.getString("Enter customer Email: ");
            Customer customer = cm.getByEmail(email);
            displayCustomer(customer);
        } catch (ServiceException e) {
            System.out.println("Cannot fetch customer with given Email. Please check logs.");
            log.warn("Error while fetching customer with email.", e);
        }
    }

    public static void displayCustomerByPhone(CustomerManager cm) {
        try {
            String phone = KeyboardUtil.getString("Enter customer Phone: ");
            Customer customer = cm.getByPhone(phone);
            displayCustomer(customer);
        } catch (ServiceException e) {
            System.out.println("Cannot fetch customer with given Phone. Please check logs.");
            log.warn("Error while fetching customer with phone.", e);
        }
    }

    public static void displayCustomerByCity(CustomerManager cm) {
        try {
            String city = KeyboardUtil.getString("Enter customer City: ");
            var customers = cm.getByCity(city);
            displayCustomersList(customers);
        } catch (ServiceException e) {
            System.out.println("Cannot fetch customer(s) with given City. Please check logs.");
            log.warn("Error while fetching customer with city.", e);
        }
    }

    public static void deleteCustomerByID(CustomerManager cm) {
        try {
            int id = KeyboardUtil.getInt("Enter customer ID to delete: ");
            cm.delete(id);
            System.out.printf("Successfully deleted Customer with ID: %d\n", id);
        } catch (ServiceException e) {
            System.out.println("Cannot delete customer with given ID. Please check logs");
            log.warn("Error while deleting customer with ID", e);
        }
    }

    public static void updateCustomerByID(CustomerManager cm) {
        try {
            int id = KeyboardUtil.getInt("Enter customer ID to update: ");
            System.out.println("Enter updated customer details: ");
            String name = KeyboardUtil.getString("Name  : ");
            String city = KeyboardUtil.getString("City  : ");
            String email = KeyboardUtil.getString("Email : ");
            String phone = KeyboardUtil.getString("Phone : ");

            Customer updatedCustomer = new Customer(id, name, city, email, phone);
            cm.update(id, updatedCustomer);

            System.out.printf("Successfully updated Customer with ID: %d\n", id);
        } catch (ServiceException | ModelException e) {
            System.out.println("Cannot update customer with given ID. Please check logs");
            log.warn("Error while updating customer with ID", e);
        }
    }
}

