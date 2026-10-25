
package com.abc.driveeasy.dao;

import com.abc.driveeasy.model.Customer;
import java.util.ArrayList;

public class CustomerDao {

    private final ArrayList<Customer> customers;

    public CustomerDao() {
        customers = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Customer findCustomerById(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equalsIgnoreCase(customerId)) {
                return customer;
            }
        }

        return null;
    }

    public ArrayList<Customer> getAllCustomers() {
        return new ArrayList<>(customers);
    }

    public boolean isCustomerIdExists(String customerId) {
        return findCustomerById(customerId) != null;
    }

    public boolean removeCustomer(String customerId) {
        Customer customer = findCustomerById(customerId);

        if (customer != null) {
            customers.remove(customer);
            return true;
        }

        return false;
    }

    public void displayAllCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }

        for (Customer customer : customers) {
            customer.displayDetails();
        }
    }
}
