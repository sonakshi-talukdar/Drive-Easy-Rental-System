import java.util.ArrayList;
import java.util.Scanner;

public class VehicleRentalManagementSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final RentalSystem rentalSystem = new RentalSystem();

    public static void main(String[] args) {
        System.out.println("====================================");
        System.out.println("       DRIVE EASY RENTAL SYSTEM");
        System.out.println("====================================");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addVehicle();
                    break;
                case 2:
                    rentalSystem.viewVehicles();
                    break;
                case 3:
                    searchVehicle();
                    break;
                case 4:
                    registerCustomer();
                    break;
                case 5:
                    rentalSystem.viewCustomers();
                    break;
                case 6:
                    createBooking();
                    break;
                case 7:
                    cancelBooking();
                    break;
                case 8:
                    makePayment();
                    break;
                case 9:
                    rentalSystem.viewReports();
                    break;
                case 10:
                    running = false;
                    System.out.println("Thank you for using Drive Easy Rental System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1-10.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n------------------------------------");
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
        System.out.println("------------------------------------");
    }

    private static void addVehicle() {
        String id = readNonEmpty("Enter vehicle ID: ");
        if (rentalSystem.findVehicle(id) != null) {
            System.out.println("Vehicle ID already exists.");
            return;
        }

        String name = readNonEmpty("Enter vehicle name/model: ");
        String type = readNonEmpty("Enter vehicle type (Car/Bike/Van/etc.): ");
        double rate = readPositiveDouble("Enter daily rental rate: ");

        Vehicle vehicle = new Vehicle(id, name, type, rate);
        rentalSystem.addVehicle(vehicle);
        System.out.println("Vehicle added successfully.");
    }

    private static void searchVehicle() {
        String id = readNonEmpty("Enter vehicle ID to search: ");
        Vehicle vehicle = rentalSystem.findVehicle(id);
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
        } else {
            vehicle.displayDetails();
        }
    }

    private static void registerCustomer() {
        String id = readNonEmpty("Enter customer ID: ");
        if (rentalSystem.findCustomer(id) != null) {
            System.out.println("Customer ID already exists.");
            return;
        }

        String name = readNonEmpty("Enter customer name: ");
        String phone = readNonEmpty("Enter phone number: ");
        String email = readNonEmpty("Enter email address: ");

        Customer customer = new Customer(id, name, phone, email);
        rentalSystem.addCustomer(customer);
        System.out.println("Customer registered successfully.");
    }

    private static void createBooking() {
        String customerId = readNonEmpty("Enter customer ID: ");
        Customer customer = rentalSystem.findCustomer(customerId);
        if (customer == null) {
            System.out.println("Customer is not registered. Please register first.");
            return;
        }

        String vehicleId = readNonEmpty("Enter vehicle ID: ");
        Vehicle vehicle = rentalSystem.findVehicle(vehicleId);
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }
        if (!vehicle.isAvailable()) {
            System.out.println("Vehicle is currently unavailable.");
            return;
        }

        int days = readPositiveInt("Enter rental duration in days: ");
        double total = vehicle.getDailyRate() * days;
        String bookingId = rentalSystem.generateBookingId();

        Booking booking = new Booking(bookingId, customer, vehicle, days, total);
        rentalSystem.addBooking(booking);
        vehicle.setAvailable(false);

        System.out.println("Booking created successfully.");
        System.out.println("Booking ID: " + bookingId);
        System.out.printf("Total rental charge: %.2f%n", total);
    }

    private static void cancelBooking() {
        String bookingId = readNonEmpty("Enter booking ID to cancel: ");
        Booking booking = rentalSystem.findBooking(bookingId);

        if (booking == null) {
            System.out.println("Booking not found.");
            return;
        }
        if (booking.getStatus() == BookingStatus.CANCELLED) {
            System.out.println("This booking has already been cancelled.");
            return;
        }

        booking.setStatus(BookingStatus.CANCELLED);
        booking.getVehicle().setAvailable(true);
        System.out.println("Booking cancelled. Vehicle is available again.");
    }

    private static void makePayment() {
        String bookingId = readNonEmpty("Enter booking ID: ");
        Booking booking = rentalSystem.findBooking(bookingId);

        if (booking == null) {
            System.out.println("Booking not found.");
            return;
        }
        if (booking.getStatus() == BookingStatus.CANCELLED) {
            System.out.println("Payment cannot be made for a cancelled booking.");
            return;
        }
        if (booking.getPaymentStatus() == PaymentStatus.PAID) {
            System.out.println("Payment for this booking has already been completed.");
            return;
        }

        System.out.println("Payment methods:");
        System.out.println("1. Cash");
        System.out.println("2. Card");
        System.out.println("3. UPI");
        int methodChoice = readInt("Choose payment method: ");

        PaymentMethod method;
        switch (methodChoice) {
            case 1:
                method = PaymentMethod.CASH;
                break;
            case 2:
                method = PaymentMethod.CARD;
                break;
            case 3:
                method = PaymentMethod.UPI;
                break;
            default:
                System.out.println("Invalid payment method.");
                return;
        }

        Payment payment = new Payment(booking.getTotalAmount(), method);
        PaymentProcessor processor = payment; // Interface reference demonstrates polymorphism.
        processor.processPayment();
        if (payment.getStatus() == PaymentStatus.PAID) {
            booking.setPaymentStatus(PaymentStatus.PAID);
            rentalSystem.addPayment(payment);
            System.out.println("Payment successful.");
            System.out.printf("Amount paid: %.2f%n", payment.getAmount());
            System.out.println("Payment method: " + payment.getMethod());
        } else {
            System.out.println("Payment failed.");
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static int readPositiveInt(String prompt) {
        while (true) {
            int value = readInt(prompt);
            if (value > 0) {
                return value;
            }
            System.out.println("Value must be greater than zero.");
        }
    }

    private static double readPositiveDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value > 0 && Double.isFinite(value)) {
                    return value;
                }
                System.out.println("Rate must be a positive number.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid amount.");
            }
        }
    }

    private static String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("This field cannot be empty.");
        }
    }
}

class RentalSystem {
    private final ArrayList<Vehicle> vehicles = new ArrayList<>();
    private final ArrayList<Customer> customers = new ArrayList<>();
    private final ArrayList<Booking> bookings = new ArrayList<>();
    private final ArrayList<Payment> payments = new ArrayList<>();
    private int nextBookingNumber = 1;

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public void addPayment(Payment payment) {
        payments.add(payment);
    }

    public Vehicle findVehicle(String id) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleId().equalsIgnoreCase(id)) {
                return vehicle;
            }
        }
        return null;
    }

    public Customer findCustomer(String id) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equalsIgnoreCase(id)) {
                return customer;
            }
        }
        return null;
    }

    public Booking findBooking(String id) {
        for (Booking booking : bookings) {
            if (booking.getBookingId().equalsIgnoreCase(id)) {
                return booking;
            }
        }
        return null;
    }

    public String generateBookingId() {
        return "B" + nextBookingNumber++;
    }

    public void viewVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles registered.");
            return;
        }
        System.out.println("\n--- Vehicle List ---");
        for (Vehicle vehicle : vehicles) {
            vehicle.displayDetails();
        }
    }

    public void viewCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers registered.");
            return;
        }
        System.out.println("\n--- Customer List ---");
        for (Customer customer : customers) {
            customer.displayDetails();
        }
    }

    public void viewReports() {
        double revenue = 0.0;
        for (Payment payment : payments) {
            if (payment.getStatus() == PaymentStatus.PAID) {
                revenue += payment.getAmount();
            }
        }

        System.out.println("\n========== REPORTS ==========");
        System.out.println("Total vehicles: " + vehicles.size());
        System.out.println("Total registered customers: " + customers.size());
        System.out.println("Total rental bookings: " + bookings.size());
        System.out.printf("Total revenue collected: %.2f%n", revenue);
        System.out.println("\n--- Booking Details ---");
        if (bookings.isEmpty()) {
            System.out.println("No bookings available.");
        } else {
            for (Booking booking : bookings) {
                booking.displayDetails();
            }
        }
        System.out.println("============================");
    }
}

class Vehicle {
    private final String vehicleId;
    private final String name;
    private final String type;
    private final double dailyRate;
    private boolean available;

    public Vehicle(String vehicleId, String name, String type, double dailyRate) {
        this.vehicleId = vehicleId;
        this.name = name;
        this.type = type;
        this.dailyRate = dailyRate;
        this.available = true;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void displayDetails() {
        System.out.printf("ID: %s | Name: %s | Type: %s | Daily Rate: %.2f | Status: %s%n",
                vehicleId, name, type, dailyRate, available ? "Available" : "Rented");
    }
}

class Customer {
    private final String customerId;
    private final String name;
    private final String phone;
    private final String email;

    public Customer(String customerId, String name, String phone, String email) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void displayDetails() {
        System.out.printf("ID: %s | Name: %s | Phone: %s | Email: %s%n",
                customerId, name, phone, email);
    }
}

enum BookingStatus {
    ACTIVE, CANCELLED
}

enum PaymentStatus {
    PENDING, PAID, FAILED
}

enum PaymentMethod {
    CASH, CARD, UPI
}

class Booking {
    private final String bookingId;
    private final Customer customer;
    private final Vehicle vehicle;
    private final int rentalDays;
    private final double totalAmount;
    private BookingStatus status;
    private PaymentStatus paymentStatus;

    public Booking(String bookingId, Customer customer, Vehicle vehicle,
                  int rentalDays, double totalAmount) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.vehicle = vehicle;
        this.rentalDays = rentalDays;
        this.totalAmount = totalAmount;
        this.status = BookingStatus.ACTIVE;
        this.paymentStatus = PaymentStatus.PENDING;
    }

    public String getBookingId() {
        return bookingId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void displayDetails() {
        System.out.printf("Booking: %s | Customer: %s | Vehicle: %s | Days: %d | Amount: %.2f | Booking Status: %s | Payment: %s%n",
                bookingId, customer.getCustomerId(), vehicle.getVehicleId(),
                rentalDays, totalAmount, status, paymentStatus);
    }
}

interface PaymentProcessor {
    void processPayment();
    double getAmount();
    PaymentMethod getMethod();
    PaymentStatus getStatus();
}

class Payment implements PaymentProcessor {
    private final double amount;
    private final PaymentMethod method;
    private PaymentStatus status;

    public Payment(double amount, PaymentMethod method) {
        this.amount = amount;
        this.method = method;
        this.status = PaymentStatus.PENDING;
    }

    public void processPayment() {
        status = PaymentStatus.PAID;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public PaymentStatus getStatus() {
        return status;
    }
}
