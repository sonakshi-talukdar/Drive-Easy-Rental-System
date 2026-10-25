
package com.abc.driveeasy.model;

public class Payment {

    private final String paymentId;
    private final Booking booking;
    private final double amount;
    private final String paymentMethod;
    private String status;

    public Payment(
            String paymentId,
            Booking booking,
            double amount,
            String paymentMethod) {

        this.paymentId = paymentId;
        this.booking = booking;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = "PENDING";
    }

    public String getPaymentId() {
        return paymentId;
    }

    public Booking getBooking() {
        return booking;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void completePayment() {
        status = "SUCCESS";
    }

    public void failPayment() {
        status = "FAILED";
    }

    public void displayDetails() {
        System.out.println("Payment ID: " + paymentId);
        System.out.println("Booking ID: " + booking.getBookingId());
        System.out.println("Amount: " + amount);
        System.out.println("Payment Method: " + paymentMethod);
        System.out.println("Payment Status: " + status);
        System.out.println("-------------------------");
    }
}
