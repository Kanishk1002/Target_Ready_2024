package com.targetindia.miniproject.service;

import com.targetindia.miniproject.dao.CustomerDao;
import com.targetindia.miniproject.dao.DaoException;
import com.targetindia.miniproject.dao.DaoFactory;
import com.targetindia.miniproject.model.Customer;

import java.util.List;

public class CustomerManager {
    private final CustomerDao dao;

    public CustomerManager() throws ServiceException {
        try {
            dao = DaoFactory.getCustomerDao();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Customer> getAll() throws ServiceException {
        try {
            return dao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Customer addNewCustomer(Customer customer) throws ServiceException {
        try {
            return dao.save(customer);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void update(int id, Customer customer) throws ServiceException {
        try {
            dao.update(id, customer);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void delete(int id) throws ServiceException {
        try {
            dao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Customer getById(int id) throws ServiceException {
        try {
            return dao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Customer getByEmail(String email) throws ServiceException {
        try {
            return dao.getByEmail(email);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Customer getByPhone(String phone) throws ServiceException {
        try {
            return dao.getByPhone(phone);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Customer> getByCity(String city) throws ServiceException {
        try {
            return dao.getByCity(city);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}