package com.targetindia.miniproject.dao;

import com.targetindia.miniproject.model.Customer;
import com.targetindia.miniproject.model.ModelException;
import com.targetindia.miniproject.utils.IDGenerator;

import java.util.ArrayList;
import java.util.List;

public class ArrayListCustomerDao implements CustomerDao {
    private final List<Customer> customers = new ArrayList<>();

    public ArrayListCustomerDao() throws ModelException {
        customers.add(new Customer(1, "Swayamshu", "Delhi", "swayamshu2k@gmail.com", "9811848966"));
        customers.add(new Customer(2, "Rahul", "Bangalore", "rahul@email.com", "8955678322"));
    }

    @Override
    public Customer save(Customer customer) throws DaoException {
        try {
            if(customer.getId() == null) {
                int newID = customers.stream()
                        .map(Customer::getId)
                        .max(Integer::compareTo)
                        .get() + 1;
                customer.setId(newID);
            }
            customers.add(customer);
            return customer;
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Customer getById(int id) throws DaoException {
        try {
            return customers.stream()
                    .filter(customer -> customer.getId().equals(id))
                    .findFirst()
                    .orElseThrow();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(int id, Customer customer) throws DaoException {
        boolean found = false;
        for(int i = 0; i < customers.size(); i++) {
            if(customers.get(i).getId() == id) {
                customers.set(i, customer);
                found = true;
                break;
            }
        }

        if(!found) {
            throw new DaoException("Customer not found for given ID: " + id);
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        boolean found = false;
        for(int i = 0; i < customers.size(); i++) {
            if(customers.get(i).getId() == id) {
                customers.remove(i);
                found = true;
                break;
            }
        }

        if(!found) {
            throw new DaoException("Customer not found for given ID: " + id);
        }
    }

    @Override
    public List<Customer> getAll() throws DaoException {
        return customers;
    }

    @Override
    public Customer getByEmail(String email) throws DaoException {
        try {
            return customers
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
            return customers
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
            return customers
                    .stream()
                    .filter(customer -> customer.getCity().equals(city))
                    .toList();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
