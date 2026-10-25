
package com.abc.driveeasy.service;

import com.abc.driveeasy.dao.BookingDao;
import com.abc.driveeasy.model.Booking;
import com.abc.driveeasy.model.Customer;
import com.abc.driveeasy.model.Vehicle;

import java.util.ArrayList;

public class BookingService {

    private final BookingDao bookingDao;
    private final CustomerService customerService;

    public BookingService(CustomerService customerService) {
        bookingDao = new BookingDao();
        this.customerService = customerService;
    }

    public boolean createBooking(
            String bookingId,
            String customerId,
            Vehicle vehicle,
            int rentalDays) {

        if (bookingId == null || bookingId.trim().isEmpty()) {
            System.out.println("Booking ID is required.");
            return false;
        }

        if (customerId == null || customerId.trim().isEmpty()) {
            System.out.println("Customer ID is required.");
            return false;
        }

        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return false;
        }

        if (rentalDays <= 0) {
            System.out.println("Rental days must be positive.");
            return false;
        }

        if (bookingDao.isBookingIdExists(bookingId)) {
            System.out.println("Booking ID already exists.");
            return false;
        }

        Customer customer = customerService.findCustomer(customerId);

        if (customer == null) {
            System.out.println("Customer not found.");
            return false;
        }

        if (!vehicle.isAvailable()) {
            System.out.println("Vehicle is not available.");
            return false;
        }

        Booking booking = new Booking(
                bookingId,
                customer,
                vehicle,
                rentalDays
        );

        vehicle.setAvailable(false);
        bookingDao.addBooking(booking);

        System.out.println("Booking created successfully.");
        System.out.println("Total amount: " + booking.getTotalAmount());

        return true;
    }

    public Booking findBooking(String bookingId) {
        return bookingDao.findBookingById(bookingId);
    }

    public ArrayList<Booking> getAllBookings() {
        return bookingDao.getAllBookings();
    }

    public boolean cancelBooking(String bookingId) {
        Booking booking = bookingDao.findBookingById(bookingId);

        if (booking == null) {
            System.out.println("Booking not found.");
            return false;
        }

        if (!booking.getStatus().equals("ACTIVE")) {
            System.out.println("Booking is already cancelled.");
            return false;
        }

        booking.cancelBooking();

        System.out.println("Booking cancelled successfully.");
        return true;
    }

    public void displayAllBookings() {
        bookingDao.displayAllBookings();
    }
}
