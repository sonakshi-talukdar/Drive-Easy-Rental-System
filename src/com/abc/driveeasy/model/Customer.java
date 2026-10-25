
package com.abc.driveeasy.model;

public class Customer {

    private final String customerId;
    private final String name;
    private final String phone;

    public Customer(String customerId, String name, String phone) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void displayDetails() {
        System.out.println("Customer ID: " + customerId);
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phone);
        System.out.println("-------------------------");
    }
}
