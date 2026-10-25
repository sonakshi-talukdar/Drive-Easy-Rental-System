
package com.abc.driveeasy.controller;

import com.abc.driveeasy.model.Booking;
import com.abc.driveeasy.model.Payment;
import com.abc.driveeasy.model.Vehicle;
import com.abc.driveeasy.service.BookingService;
import com.abc.driveeasy.service.CustomerService;
import com.abc.driveeasy.service.PaymentService;
import com.abc.driveeasy.service.VehicleService;

public class ReportsController {

    private final VehicleService vehicleService;
    private final CustomerService customerService;
    private final BookingService bookingService;
    private final PaymentService paymentService;

    public ReportsController(
            VehicleService vehicleService,
            CustomerService customerService,
            BookingService bookingService,
            PaymentService paymentService) {

        this.vehicleService = vehicleService;
        this.customerService = customerService;
        this.bookingService = bookingService;
        this.paymentService = paymentService;
    }

    public void displayReport() {

        System.out.println("\n===== DRIVE EASY REPORT =====");

        System.out.println(
                "Total Vehicles: "
                + vehicleService.getAllVehicles().size()
        );

        System.out.println(
                "Total Customers: "
                + customerService.getAllCustomers().size()
        );

        System.out.println(
                "Total Bookings: "
                + bookingService.getAllBookings().size()
        );

        System.out.println(
                "Total Payments: "
                + paymentService.getAllPayments().size()
        );

        double totalRevenue = 0;

        for (Payment payment : paymentService.getAllPayments()) {

            if (payment.getStatus().equals("SUCCESS")) {
                totalRevenue += payment.getAmount();
            }
        }

        System.out.println(
                "Total Revenue: Rs. " + totalRevenue
        );

        System.out.println("\n===== BOOKING DETAILS =====");

        for (Booking booking : bookingService.getAllBookings()) {
            booking.displayDetails();
        }

        System.out.println("\n===== VEHICLE DETAILS =====");

        for (Vehicle vehicle : vehicleService.getAllVehicles()) {
            vehicle.displayDetails();
        }

        System.out.println("============================");
    }
}
