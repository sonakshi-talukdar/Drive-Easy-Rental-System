
package com.abc.driveeasy.controller;

import com.abc.driveeasy.model.Customer;
import com.abc.driveeasy.service.CustomerService;

import java.util.ArrayList;

public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public boolean registerCustomer(
            String customerId,
            String name,
            String phone) {

        return customerService.registerCustomer(
                customerId,
                name,
                phone
        );
    }

    public Customer searchCustomer(String customerId) {
        Customer customer = customerService.findCustomer(customerId);

        if (customer == null) {
            System.out.println("Customer not found.");
        } else {
            customer.displayDetails();
        }

        return customer;
    }

    public ArrayList<Customer> viewAllCustomers() {
        customerService.displayAllCustomers();

        return customerService.getAllCustomers();
    }

    public boolean removeCustomer(String customerId) {
        boolean removed = customerService.removeCustomer(customerId);

        if (removed) {
            System.out.println("Customer removed successfully.");
        } else {
            System.out.println("Customer not found.");
        }

        return removed;
    }
}
