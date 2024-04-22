package com.targetindia.miniproject.dao;

import com.targetindia.miniproject.model.Customer;
import com.targetindia.miniproject.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class JPACustomerDao implements CustomerDao {
    @Override
    public Customer save(Customer customer) throws DaoException {
        try (var em = JPAUtil.createEntityManager();){
            var tx = em.getTransaction();
            try {
                tx.begin();
                em.persist(customer);
                tx.commit();
                return customer;
            } catch (Exception e) {
                tx.rollback();
                throw new DaoException(e);
            }
        } catch (Exception e) {
            log.warn("Error while calling JPACustomerDao.save().", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Customer getById(int id) throws DaoException {
        try (EntityManager em = JPAUtil.createEntityManager()){
            Customer customer = em.find(Customer.class, id);
            if (customer == null) {
                throw new DaoException("Customer not found for given ID: " + id);
            }
            return customer;
        } catch (Exception e) {
            log.warn("Error while calling JPACustomerDao.getById().", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void update(int id, Customer customer) throws DaoException {
        try (EntityManager em = JPAUtil.createEntityManager()){
            var tx = em.getTransaction();
            try {
                tx.begin();
                Customer existingCustomer = em.find(Customer.class, id);
                if(existingCustomer == null) {
                    throw new DaoException("Customer not found for given ID: " + id);
                }
                em.remove(existingCustomer);
                em.persist(customer);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                throw new DaoException(e);
            }
        } catch (Exception e) {
            log.warn("Error while calling JPACustomerDao.update().", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        try (EntityManager em = JPAUtil.createEntityManager()){
            var tx = em.getTransaction();
            try {
                tx.begin();
                Customer customer = em.find(Customer.class, id);
                if(customer == null) {
                    throw new DaoException("Customer not found for given ID: " + id);
                }
                em.remove(customer);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                throw new DaoException(e);
            }
        } catch (Exception e) {
            log.warn("Error while calling JPACustomerDao.delete().", e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<Customer> getAll() throws DaoException {
        try (EntityManager em = JPAUtil.createEntityManager();){
            return em.createQuery("SELECT * FROM Customers", Customer.class)
                    .getResultList();
        } catch (Exception e) {
            log.warn("Error while calling JPACustomerDao.getAll().", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Customer getByEmail(String email) throws DaoException {
        try (EntityManager em = JPAUtil.createEntityManager()){
            TypedQuery<Customer> query = em.createQuery("SELECT * FROM Customers c WHERE c.Email = :email", Customer.class);
            return query.setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            log.warn("Error while calling JPACustomerDao.getByEmail().", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Customer getByPhone(String phone) throws DaoException {
        try (EntityManager em = JPAUtil.createEntityManager()){
            TypedQuery<Customer> query = em.createQuery("SELECT * FROM Customers c WHERE c.Phone = :phone", Customer.class);
            return query.setParameter("phone", phone)
                    .getSingleResult();
        } catch (Exception e) {
            log.warn("Error while calling JPACustomerDao.getByPhone().", e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<Customer> getByCity(String city) throws DaoException {
        try (EntityManager em = JPAUtil.createEntityManager()){
            TypedQuery<Customer> query = em.createQuery("SELECT * FROM Customers c WHERE c.City = :city", Customer.class);
            return query.setParameter("city", city).getResultList();
        } catch (Exception e) {
            log.warn("Error while calling JPACustomerDao.getByCity().", e);
            throw new DaoException(e);
        }
    }
}
