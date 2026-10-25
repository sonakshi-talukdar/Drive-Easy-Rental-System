
package com.abc.driveeasy;

import com.abc.driveeasy.controller.BookingController;
import com.abc.driveeasy.controller.CustomerController;
import com.abc.driveeasy.controller.PaymentsController;
import com.abc.driveeasy.controller.ReportsController;
import com.abc.driveeasy.controller.VehicleController;

import com.abc.driveeasy.model.Booking;
import com.abc.driveeasy.model.Vehicle;

import com.abc.driveeasy.service.BookingService;
import com.abc.driveeasy.service.CustomerService;
import com.abc.driveeasy.service.PaymentService;
import com.abc.driveeasy.service.VehicleService;

import java.util.Scanner;

public class DriveEasyMain {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Create shared service objects
        CustomerService customerService = new CustomerService();
        VehicleService vehicleService = new VehicleService();
        BookingService bookingService =
                new BookingService(customerService);
        PaymentService paymentService = new PaymentService();

        // Create controllers
        CustomerController customerController =
                new CustomerController(customerService);

        VehicleController vehicleController =
                new VehicleController(vehicleService);

        BookingController bookingController =
                new BookingController(bookingService);

        PaymentsController paymentsController =
                new PaymentsController(paymentService);

        ReportsController reportsController =
                new ReportsController(
                        vehicleService,
                        customerService,
                        bookingService,
                        paymentService
                );

        int choice;

        do {

            System.out.println("\n===== DRIVE EASY RENTAL SYSTEM =====");
            System.out.println("1. Add Vehicle");
            System.out.println("2. View Vehicles");
            System.out.println("3. Search Vehicle");
            System.out.println("4. Register Customer");
            System.out.println("5. View Customers");
            System.out.println("6. Create Rental Booking");
            System.out.println("7. Cancel Booking");
            System.out.println("8. Make Payment");
            System.out.println("9. View Reports");
            System.out.println("10. Exit");

            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                scanner.next();
                System.out.print("Enter your choice: ");
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter Vehicle ID: ");
                    String vehicleId = scanner.nextLine();

                    System.out.print("Enter Vehicle Name: ");
                    String vehicleName = scanner.nextLine();

                    System.out.print("Enter Vehicle Type: ");
                    String vehicleType = scanner.nextLine();

                    System.out.print("Enter Daily Rate: ");

                    while (!scanner.hasNextDouble()) {
                        System.out.println("Enter a valid rate.");
                        scanner.next();
                        System.out.print("Enter Daily Rate: ");
                    }

                    double dailyRate = scanner.nextDouble();
                    scanner.nextLine();

                    vehicleController.addVehicle(
                            vehicleId,
                            vehicleName,
                            vehicleType,
                            dailyRate
                    );

                    break;

                case 2:

                    vehicleController.viewAllVehicles();
                    break;

                case 3:

                    System.out.print("Enter Vehicle ID: ");
                    String searchVehicleId = scanner.nextLine();

                    vehicleController.searchVehicle(
                            searchVehicleId
                    );

                    break;

                case 4:

                    System.out.print("Enter Customer ID: ");
                    String customerId = scanner.nextLine();

                    System.out.print("Enter Customer Name: ");
                    String customerName = scanner.nextLine();

                    System.out.print("Enter Phone Number: ");
                    String phone = scanner.nextLine();

                    customerController.registerCustomer(
                            customerId,
                            customerName,
                            phone
                    );

                    break;

                case 5:

                    customerController.viewAllCustomers();
                    break;

                case 6:

                    System.out.print("Enter Booking ID: ");
                    String bookingId = scanner.nextLine();

                    System.out.print("Enter Customer ID: ");
                    String bookingCustomerId = scanner.nextLine();

                    System.out.print("Enter Vehicle ID: ");
                    String bookingVehicleId = scanner.nextLine();

                    Vehicle bookingVehicle =
                            vehicleController.searchVehicle(
                                    bookingVehicleId
                            );

                    if (bookingVehicle != null) {

                        System.out.print("Enter Rental Days: ");

                        while (!scanner.hasNextInt()) {
                            System.out.println(
                                    "Enter a valid number of days."
                            );

                            scanner.next();
                            System.out.print("Enter Rental Days: ");
                        }

                        int rentalDays = scanner.nextInt();
                        scanner.nextLine();

                        bookingController.createBooking(
                                bookingId,
                                bookingCustomerId,
                                bookingVehicle,
                                rentalDays
                        );
                    }

                    break;

                case 7:

                    System.out.print("Enter Booking ID: ");
                    String cancelBookingId = scanner.nextLine();

                    bookingController.cancelBooking(
                            cancelBookingId
                    );

                    break;

                case 8:

                    System.out.print("Enter Booking ID: ");
                    String paymentBookingId = scanner.nextLine();

                    Booking paymentBooking =
                            bookingController.searchBooking(
                                    paymentBookingId
                            );

                    if (paymentBooking != null) {

                        System.out.print("Enter Payment ID: ");
                        String paymentId = scanner.nextLine();

                        System.out.println(
                                "Payment Methods: CASH, CARD, UPI"
                        );

                        System.out.print("Enter Payment Method: ");
                        String paymentMethod = scanner.nextLine();

                        paymentsController.makePayment(
                                paymentId,
                                paymentBooking,
                                paymentMethod
                        );
                    }

                    break;

                case 9:

                    reportsController.displayReport();
                    break;

                case 10:

                    System.out.println(
                            "Thank you for using Drive Easy!"
                    );

                    break;

                default:

                    System.out.println("Invalid choice.");
            }

        } while (choice != 10);

        scanner.close();
    }
}
