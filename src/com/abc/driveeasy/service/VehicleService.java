
package com.abc.driveeasy.service;

import com.abc.driveeasy.dao.VehicleDao;
import com.abc.driveeasy.model.Vehicle;

import java.util.ArrayList;

public class VehicleService {

    private final VehicleDao vehicleDao;

    public VehicleService() {
        vehicleDao = new VehicleDao();
    }

    public boolean addVehicle(
            String vehicleId,
            String name,
            String type,
            double dailyRate) {

        if (vehicleId == null || vehicleId.trim().isEmpty()
                || name == null || name.trim().isEmpty()
                || type == null || type.trim().isEmpty()) {

            System.out.println("All vehicle details are required.");
            return false;
        }

        if (dailyRate <= 0) {
            System.out.println("Daily rate must be positive.");
            return false;
        }

        if (vehicleDao.isVehicleIdExists(vehicleId)) {
            System.out.println("Vehicle ID already exists.");
            return false;
        }

        Vehicle vehicle = new Vehicle(
                vehicleId,
                name,
                type,
                dailyRate
        );

        vehicleDao.addVehicle(vehicle);

        System.out.println("Vehicle added successfully.");
        return true;
    }

    public Vehicle findVehicle(String vehicleId) {
        return vehicleDao.findVehicleById(vehicleId);
    }

    public ArrayList<Vehicle> getAllVehicles() {
        return vehicleDao.getAllVehicles();
    }

    public void displayAllVehicles() {
        vehicleDao.displayAllVehicles();
    }

    public boolean removeVehicle(String vehicleId) {
        return vehicleDao.removeVehicle(vehicleId);
    }
}
