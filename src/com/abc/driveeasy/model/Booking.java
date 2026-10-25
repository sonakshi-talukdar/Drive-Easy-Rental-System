
package com.abc.driveeasy.model;

public class Booking {

    private final String bookingId;
    private final Customer customer;
    private final Vehicle vehicle;
    private final int rentalDays;
    private final double totalAmount;
    private String status;

    public Booking(
            String bookingId,
            Customer customer,
            Vehicle vehicle,
            int rentalDays) {

        this.bookingId = bookingId;
        this.customer = customer;
        this.vehicle = vehicle;
        this.rentalDays = rentalDays;
        this.totalAmount = vehicle.getDailyRate() * rentalDays;
        this.status = "ACTIVE";
    }

    public String getBookingId() {
        return bookingId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void cancelBooking() {
        if (status.equals("ACTIVE")) {
            status = "CANCELLED";
            vehicle.setAvailable(true);
        }
    }

    public void displayDetails() {
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Customer: " + customer.getName());
        System.out.println("Vehicle: " + vehicle.getName());
        System.out.println("Rental Days: " + rentalDays);
        System.out.println("Total Amount: " + totalAmount);
        System.out.println("Status: " + status);
        System.out.println("-------------------------");
    }
}
