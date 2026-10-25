
package com.abc.driveeasy.service;

import com.abc.driveeasy.dao.PaymentDao;
import com.abc.driveeasy.model.Booking;
import com.abc.driveeasy.model.Payment;

import java.util.ArrayList;

public class PaymentService {

    private final PaymentDao paymentDao;

    public PaymentService() {
        paymentDao = new PaymentDao();
    }

    public boolean processPayment(
            String paymentId,
            Booking booking,
            String paymentMethod) {

        if (paymentId == null || paymentId.trim().isEmpty()) {
            System.out.println("Payment ID is required.");
            return false;
        }

        if (booking == null) {
            System.out.println("Booking not found.");
            return false;
        }

        if (paymentMethod == null || paymentMethod.trim().isEmpty()) {
            System.out.println("Payment method is required.");
            return false;
        }

        if (paymentDao.isPaymentIdExists(paymentId)) {
            System.out.println("Payment ID already exists.");
            return false;
        }

        if (!booking.getStatus().equals("ACTIVE")) {
            System.out.println("Cannot pay for an inactive booking.");
            return false;
        }

        Payment payment = new Payment(
                paymentId,
                booking,
                booking.getTotalAmount(),
                paymentMethod
        );

        payment.completePayment();
        paymentDao.addPayment(payment);

        System.out.println("Payment successful.");
        System.out.println("Amount paid: " + payment.getAmount());

        return true;
    }

    public Payment findPayment(String paymentId) {
        return paymentDao.findPaymentById(paymentId);
    }

    public ArrayList<Payment> getAllPayments() {
        return paymentDao.getAllPayments();
    }

    public void displayAllPayments() {
        paymentDao.displayAllPayments();
    }
}
