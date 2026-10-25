
package com.abc.driveeasy.controller;

import com.abc.driveeasy.model.Booking;
import com.abc.driveeasy.model.Payment;
import com.abc.driveeasy.service.PaymentService;

import java.util.ArrayList;

public class PaymentsController {

    private final PaymentService paymentService;

    public PaymentsController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public boolean makePayment(
            String paymentId,
            Booking booking,
            String paymentMethod) {

        return paymentService.processPayment(
                paymentId,
                booking,
                paymentMethod
        );
    }

    public Payment searchPayment(String paymentId) {
        Payment payment = paymentService.findPayment(paymentId);

        if (payment == null) {
            System.out.println("Payment not found.");
        } else {
            payment.displayDetails();
        }

        return payment;
    }

    public ArrayList<Payment> viewAllPayments() {
        paymentService.displayAllPayments();

        return paymentService.getAllPayments();
    }
}
