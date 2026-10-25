
package com.abc.driveeasy.model;

public class Vehicle {

    private final String vehicleId;
    private final String name;
    private final String type;
    private final double dailyRate;
    private boolean available;

    public Vehicle(String vehicleId, String name,
                  String type, double dailyRate) {

        this.vehicleId = vehicleId;
        this.name = name;
        this.type = type;
        this.dailyRate = dailyRate;
        this.available = true;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
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
        System.out.printf(
            "ID: %s | Name: %s | Type: %s | Daily Rate: %.2f | Status: %s%n",
            vehicleId,
            name,
            type,
            dailyRate,
            available ? "Available" : "Rented"
        );
    }
}
