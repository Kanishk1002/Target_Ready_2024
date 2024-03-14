package com.targetindia.model;

import com.targetindia.exceptions.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CRUDOperator {
    private static final String FILE_PATH = "customers.csv";

    public static void printData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));) {
            String line;
            System.out.println();
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addData(String rowData) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true));) {
            writer.write(rowData);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void processAddData() throws IOException {
        int id = Customer.readId("Enter customer Id: ");
        String name = Customer.readName("Enter customer name: ");
        String city = Customer.readCity("Enter customer city: ");
        String email = Customer.readEmail("Enter customer email: ");
        String number = Customer.readNumber("Enter customer number: ");

        addData(Objects.toString(id) + "," +
                name + "," +
                city + "," +
                email + "," +
                number);
        System.out.println("\nAdded new customer data!");
    }

    private static void searchData(String city) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));) {
            String line;
            System.out.println();
            while((line = reader.readLine()) != null) {
                String[] cells = line.split(",");
                if(cells[2].equals(city)) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void processSearchData() throws InvalidCityException {
        String city = Customer.readCity("Enter customer city: ");
        searchData(city);
    }

    private static List<String> readData() {
        List<String> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    private static void writeData(List<String> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String entry : data) {
                writer.write(entry);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void updateData(int id, String newData) {
        List<String> records = readData();
        for(int i = 1; i < records.size(); i++) {
            int _id = Integer.parseInt(records.get(i).split(",", 2)[0]);
            if(_id == id) {
                records.set(i, newData);
                writeData(records);
                return;
            }
        }
    }

    public static void processUpdateData() throws InvalidIdException {
        int _id = Customer.readId("Enter customer Id to be updated: ");
        int id = Customer.readId("Enter new customer Id: ");
        String name = Customer.readName("Enter new customer name: ");
        String city = Customer.readCity("Enter new customer city: ");
        String email = Customer.readEmail("Enter new customer email: ");
        String number = Customer.readNumber("Enter new customer number: ");

        updateData(_id, Objects.toString(id) + "," +
                name + "," +
                city + "," +
                email + "," +
                number);
        System.out.println("\nUpdated customer data!");
    }

    private static void deleteData(int id) {
        List<String> records = readData();
        for(int i = 1; i < records.size(); i++) {
            int _id = Integer.parseInt((records.get(i)).split(",", 2)[0]);
            if(_id == id) {
                records.remove(i);
                writeData(records);
                return;
            }
        }
    }

    public static void processDeleteData() throws InvalidIdException {
        int id = Customer.readId("Enter customer Id: ");
        deleteData(id);
    }
}
