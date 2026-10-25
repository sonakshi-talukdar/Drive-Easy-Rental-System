
package com.abc.driveeasy.controller;

import com.abc.driveeasy.model.Booking;
import com.abc.driveeasy.model.Vehicle;
import com.abc.driveeasy.service.BookingService;

import java.util.ArrayList;

public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public boolean createBooking(
            String bookingId,
            String customerId,
            Vehicle vehicle,
            int rentalDays) {

        return bookingService.createBooking(
                bookingId,
                customerId,
                vehicle,
                rentalDays
        );
    }

    public Booking searchBooking(String bookingId) {
        Booking booking = bookingService.findBooking(bookingId);

        if (booking == null) {
            System.out.println("Booking not found.");
        } else {
            booking.displayDetails();
        }

        return booking;
    }

    public boolean cancelBooking(String bookingId) {
        return bookingService.cancelBooking(bookingId);
    }

    public ArrayList<Booking> viewAllBookings() {
        bookingService.displayAllBookings();

        return bookingService.getAllBookings();
    }
}
