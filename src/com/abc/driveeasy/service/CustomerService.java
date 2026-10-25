
package com.abc.driveeasy.service;

import com.abc.driveeasy.dao.CustomerDao;
import com.abc.driveeasy.model.Customer;

import java.util.ArrayList;

public class CustomerService {

    private final CustomerDao customerDao;

    public CustomerService() {
        customerDao = new CustomerDao();
    }

    public boolean registerCustomer(
            String customerId,
            String name,
            String phone) {

        if (customerId == null || customerId.trim().isEmpty()
                || name == null || name.trim().isEmpty()
                || phone == null || phone.trim().isEmpty()) {

            System.out.println("All customer details are required.");
            return false;
        }

        if (customerDao.isCustomerIdExists(customerId)) {
            System.out.println("Customer ID already exists.");
            return false;
        }

        Customer customer = new Customer(
                customerId,
                name,
                phone
        );

        customerDao.addCustomer(customer);

        System.out.println("Customer registered successfully.");
        return true;
    }

    public Customer findCustomer(String customerId) {
        return customerDao.findCustomerById(customerId);
    }

    public ArrayList<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    public void displayAllCustomers() {
        customerDao.displayAllCustomers();
    }

    public boolean removeCustomer(String customerId) {
        return customerDao.removeCustomer(customerId);
    }
}
