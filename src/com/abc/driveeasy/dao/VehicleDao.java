
package com.abc.driveeasy.dao;

import com.abc.driveeasy.model.Vehicle;
import java.util.ArrayList;

public class VehicleDao {

    private final ArrayList<Vehicle> vehicles;

    public VehicleDao() {
        vehicles = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public Vehicle findVehicleById(String vehicleId) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleId().equalsIgnoreCase(vehicleId)) {
                return vehicle;
            }
        }

        return null;
    }

    public ArrayList<Vehicle> getAllVehicles() {
        return new ArrayList<>(vehicles);
    }

    public boolean isVehicleIdExists(String vehicleId) {
        return findVehicleById(vehicleId) != null;
    }

    public boolean removeVehicle(String vehicleId) {
        Vehicle vehicle = findVehicleById(vehicleId);

        if (vehicle != null) {
            vehicles.remove(vehicle);
            return true;
        }

        return false;
    }

    public void displayAllVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }

        for (Vehicle vehicle : vehicles) {
            vehicle.displayDetails();
        }
    }
}
