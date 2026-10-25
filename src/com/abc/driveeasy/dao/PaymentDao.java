
package com.abc.driveeasy.dao;

import com.abc.driveeasy.model.Payment;
import java.util.ArrayList;

public class PaymentDao {

    private final ArrayList<Payment> payments;

    public PaymentDao() {
        payments = new ArrayList<>();
    }

    public void addPayment(Payment payment) {
        payments.add(payment);
    }

    public Payment findPaymentById(String paymentId) {
        for (Payment payment : payments) {
            if (payment.getPaymentId().equalsIgnoreCase(paymentId)) {
                return payment;
            }
        }

        return null;
    }

    public ArrayList<Payment> getAllPayments() {
        return new ArrayList<>(payments);
    }

    public boolean isPaymentIdExists(String paymentId) {
        return findPaymentById(paymentId) != null;
    }

    public void displayAllPayments() {
        if (payments.isEmpty()) {
            System.out.println("No payments found.");
            return;
        }

        for (Payment payment : payments) {
            payment.displayDetails();
        }
    }
}
