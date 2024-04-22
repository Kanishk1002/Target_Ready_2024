package com.targetindia.miniproject.dao;

import com.targetindia.miniproject.model.Customer;
import com.targetindia.miniproject.model.ModelException;
import com.targetindia.miniproject.utils.IDGenerator;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class CsvFileCustomerDao implements CustomerDao {
    private static final String FILENAME = "customer-data.csv";
    private final Map<Integer, Customer> customerMap = new LinkedHashMap<>();

    public CsvFileCustomerDao() {
        loadCSVFile();
    }

    private void loadCSVFile() {
        try (
                FileReader file = new FileReader(FILENAME);
                BufferedReader in = new BufferedReader(file);
                ){
            // skip first line i.e. csv header
            in.readLine();
            String line;
            while((line = in.readLine()) != null) {
                Customer customer = fromCSV(line);
                assert customer != null;
                customerMap.put(customer.getId(), customer);
            }
        } catch (Exception e) {
            log.warn("Error while loading csv data into customer map.", e);
        }
    }

    private void saveToFile() {
        try (
                FileWriter file = new FileWriter(FILENAME);
                PrintWriter out = new PrintWriter(file);
                ) {
            out.println("ID,Name,City,Email,Phone");
            customerMap.values()
                    .stream()
                    .map(this::toCSV)
                    .forEach(out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Customer fromCSV(String line) {
        try {
            String[] args = line.split(",");
            return new Customer(
                    Integer.parseInt(args[0]),
                    args[1],
                    args[2],
                    args[3],
                    args[4]
            );
        } catch (NumberFormatException | ModelException e) {
            log.warn("Error while converting csv data into customer object.", e);
            return null;
        }
    }

    private String toCSV(Customer customer) {
        if(customer == null) {
            return null;
        }
        return String.format("%s,%s,%s,%s,%s",
                customer.getId(),
                customer.getName(),
                customer.getCity(),
                customer.getEmail(),
                customer.getPhone());
    }

    @Override
    public Customer save(Customer customer) throws DaoException {
        try {
            if(customer.getId() == null) {
                customer.setId(IDGenerator.generate());
            }
            customerMap.put(customer.getId(), customer);
            saveToFile();
            return customer;
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Customer getById(int id) throws DaoException {
        try {
            if(customerMap.containsKey(id)) {
                return customerMap.get(id);
            } else {
                throw new DaoException("Customer not found for given ID: " + id);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(int id, Customer customer) throws DaoException {
        try {
            if (customerMap.containsKey(id)) {
                customerMap.put(id, customer);
                saveToFile();
            } else {
                throw new DaoException("Customer not found for given ID: " + id);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        try {
            if(customerMap.containsKey(id)) {
                customerMap.remove(id);
                saveToFile();
            } else {
                throw new DaoException("Customer not found for given ID: " + id);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Customer> getAll() throws DaoException {
        try {
            return customerMap.values().stream().toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer getByEmail(String email) throws DaoException {
        try {
            return customerMap.values()
                    .stream()
                    .filter(customer -> customer.getEmail().equals(email))
                    .findFirst()
                    .orElseThrow();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Customer getByPhone(String phone) throws DaoException {
        try {
            return customerMap.values()
                    .stream()
                    .filter(customer -> customer.getPhone().equals(phone))
                    .findFirst()
                    .orElseThrow();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Customer> getByCity(String city) throws DaoException {
        try {
            return customerMap.values()
                    .stream()
                    .filter(customer -> customer.getCity().equals(city))
                    .toList();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
