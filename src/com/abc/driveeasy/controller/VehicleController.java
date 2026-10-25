
package com.abc.driveeasy.controller;

import com.abc.driveeasy.model.Vehicle;
import com.abc.driveeasy.service.VehicleService;

import java.util.ArrayList;

public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public void addVehicle(
            String vehicleId,
            String name,
            String type,
            double dailyRate) {

        vehicleService.addVehicle(
                vehicleId,
                name,
                type,
                dailyRate
        );
    }

    public Vehicle searchVehicle(String vehicleId) {
        Vehicle vehicle = vehicleService.findVehicle(vehicleId);

        if (vehicle == null) {
            System.out.println("Vehicle not found.");
        } else {
            vehicle.displayDetails();
        }

        return vehicle;
    }

    public ArrayList<Vehicle> viewAllVehicles() {
        vehicleService.displayAllVehicles();

        return vehicleService.getAllVehicles();
    }

    public boolean removeVehicle(String vehicleId) {
        boolean removed = vehicleService.removeVehicle(vehicleId);

        if (removed) {
            System.out.println("Vehicle removed successfully.");
        } else {
            System.out.println("Vehicle not found.");
        }

        return removed;
    }
}
