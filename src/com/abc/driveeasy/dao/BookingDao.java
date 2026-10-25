
package com.abc.driveeasy.dao;

import com.abc.driveeasy.model.Booking;
import java.util.ArrayList;

public class BookingDao {

    private final ArrayList<Booking> bookings;

    public BookingDao() {
        bookings = new ArrayList<>();
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public Booking findBookingById(String bookingId) {
        for (Booking booking : bookings) {
            if (booking.getBookingId().equalsIgnoreCase(bookingId)) {
                return booking;
            }
        }

        return null;
    }

    public ArrayList<Booking> getAllBookings() {
        return new ArrayList<>(bookings);
    }

    public boolean removeBooking(String bookingId) {
        Booking booking = findBookingById(bookingId);

        if (booking != null) {
            bookings.remove(booking);
            return true;
        }

        return false;
    }

    public boolean isBookingIdExists(String bookingId) {
        return findBookingById(bookingId) != null;
    }

    public void displayAllBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        for (Booking booking : bookings) {
            booking.displayDetails();
        }
    }
}
