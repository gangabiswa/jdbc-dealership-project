package com.yearup.dealership.db;

import com.yearup.dealership.models.Vehicle;
import jdk.internal.icu.text.UnicodeSet;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private DataSource dataSource;

    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicle(Vehicle vehicle) {
        // TODO: Implement the logic to add a vehicle

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO inventory (vin, make, model, year, price, color, Odometer, type) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {

            // Set the parameters in the SQL query from the vehicle object
            preparedStatement.setString(1, vehicle.getVin());
            preparedStatement.setString(2, vehicle.getMake());
            preparedStatement.setString(3, vehicle.getModel());
            preparedStatement.setInt(4, vehicle.getYear());
            preparedStatement.setDouble(5, vehicle.getPrice());
            preparedStatement.setString(6, vehicle.getColor());
            preparedStatement.setString(7, vehicle.getOdometer());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Vehicle added to inventory successfully.");
            } else {
                System.out.println("Failed to add vehicle to inventory.");
            }

        } catch (SQLException ex) {
            System.err.println("An error occurred while trying to add the vehicle to inventory.");
            ex.printStackTrace();
        }
    }

    public void removeVehicle(String vin) {
        if (vin == null || vin.isEmpty()) {
            System.out.println("Invalid VIN provided.");
            return;
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM inventory WHERE vin = ?")) {
            preparedStatement.setString(1, vin);
            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Vehicle removed from inventory successfully.");
            } else {
                System.out.println("Failed to remove vehicle from inventory. Vehicle not found.");
            }

        } catch (SQLException ex) {
            System.err.println("An error occurred while trying to remove the vehicle from inventory.");
            ex.printStackTrace();
        }

    }

    public List<Vehicle> searchByPriceRange(double minPrice, double maxPrice) {
        // TODO: Implement the logic to search vehicles by price range

        try( Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM inventory WHERE price BETWEEN ? AND ?")) {

            preparedStatement.setDouble(1, minPrice);
            preparedStatement.setDouble(2, maxPrice);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Vehicle vehicle = new Vehicle();
                    vehicle.setVin(resultSet.getString("vin"));
                    vehicle.setMake(resultSet.getString("make"));
                    vehicle.setModel(resultSet.getString("model"));
                    vehicle.setYear(resultSet.getInt("year"));
                    vehicle.setPrice(resultSet.getDouble("price"));
                    vehicle.setColor(resultSet.getString("color"));
                    vehicle.setOdometer(resultSet.getInt("Odometer"));;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Vehicle> searchByMakeModel(String make, String model) {
        // TODO: Implement the logic to search vehicles by make and model
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM inventory WHERE make = ? AND model = ?")) {

            // Set the parameters in the SQL query
            preparedStatement.setString(1, make);
            preparedStatement.setString(2, model);

            // Execute the query and process the result set
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Vehicle vehicle = new Vehicle();
                    vehicle.setVin(resultSet.getString("vin"));
                    vehicle.setMake(resultSet.getString("make"));
                    vehicle.setModel(resultSet.getString("model"));
                    vehicle.setYear(resultSet.getInt("year"));
                    vehicle.setPrice(resultSet.getDouble("price"));
                    vehicle.setColor(resultSet.getString("color"));
                    vehicle.setOdometer(resultSet.getInt("Odometer"));

                }
            }

        } catch (SQLException ex) {
            // Log the exception with a meaningful message
            System.err.println("An error occurred while trying to search for vehicles by make and model.");
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Vehicle> searchByYearRange(int minYear, int maxYear) {
        // TODO: Implement the logic to search vehicles by year range
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM inventory WHERE year BETWEEN ? AND ?")) {

            preparedStatement.setInt(1, minYear);
            preparedStatement.setInt(2, maxYear);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Vehicle vehicle = new Vehicle();
                    vehicle.setVin(resultSet.getString("vin"));
                    vehicle.setMake(resultSet.getString("make"));
                    vehicle.setModel(resultSet.getString("model"));
                    vehicle.setYear(resultSet.getInt("year"));
                    vehicle.setPrice(resultSet.getDouble("price"));
                    vehicle.setColor(resultSet.getString("color"));
                    vehicle.setOdometer(resultSet.getInt("Odometer"));
                }
            }
        } catch (Exception ex) {
            System.err.println("An error occurred while trying to search for vehicles by year range.");
            ex.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByColor(String color) {
        // TODO: Implement the logic to search vehicles by color
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM inventory WHERE color = ?")) {

            preparedStatement.setString(1, color);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Vehicle vehicle = new Vehicle();
                    vehicle.setVin(resultSet.getString("vin"));
                    vehicle.setMake(resultSet.getString("make"));
                    vehicle.setModel(resultSet.getString("model"));
                    vehicle.setYear(resultSet.getInt("year"));
                    vehicle.setPrice(resultSet.getDouble("price"));
                    vehicle.setColor(resultSet.getString("color"));
                    vehicle.setOdometer(resultSet.getInt("Odometer"));
                }
            }
        } catch (Exception ex) {
            System.err.println("An error occurred while trying to search for vehicles by color.");
            ex.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByMileageRange(int minMileage, int maxMileage) {
        // TODO: Implement the logic to search vehicles by mileage range
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM inventory WHERE mileage BETWEEN ? AND ?")) {

            preparedStatement.setInt(1, minMileage);
            preparedStatement.setInt(2, maxMileage);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Vehicle vehicle = new Vehicle();
                    vehicle.setVin(resultSet.getString("vin"));
                    vehicle.setMake(resultSet.getString("make"));
                    vehicle.setModel(resultSet.getString("model"));
                    vehicle.setYear(resultSet.getInt("year"));
                    vehicle.setPrice(resultSet.getDouble("price"));
                    vehicle.setColor(resultSet.getString("color"));
                    vehicle.setOdometer(resultSet.getInt("Odometer"));
                }
            }

        } catch (Exception ex) {
            System.err.println("An error occurred while trying to search for vehicles by mileage range.");
            ex.printStackTrace();
        }
        return vehicles;

    }

    public List<Vehicle> searchByType(String type) {
        // TODO: Implement the logic to search vehicles by type
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM inventory WHERE type = ?")) {

            preparedStatement.setString(1, type);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Vehicle vehicle = new Vehicle();
                    vehicle.setVin(resultSet.getString("vin"));
                    vehicle.setMake(resultSet.getString("make"));
                    vehicle.setModel(resultSet.getString("model"));
                    vehicle.setYear(resultSet.getInt("year"));
                    vehicle.setPrice(resultSet.getDouble("price"));
                    vehicle.setColor(resultSet.getString("color"));
                    vehicle.setOdometer(resultSet.getInt("Odometer"));
                }
            }

        } catch (Exception ex) {
            System.err.println("An error occurred while trying to search for vehicles by type.");
            ex.printStackTrace();
        }
        return vehicles;
    }

    private Vehicle createVehicleFromResultSet(ResultSet resultSet) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVin(resultSet.getString("VIN"));
        vehicle.setMake(resultSet.getString("make"));
        vehicle.setModel(resultSet.getString("model"));
        vehicle.setYear(resultSet.getInt("year"));
        vehicle.setSold(resultSet.getBoolean("SOLD"));
        vehicle.setColor(resultSet.getString("color"));
        vehicle.setVehicleType(resultSet.getString("vehicleType"));
        vehicle.setOdometer(resultSet.getInt("odometer"));
        vehicle.setPrice(resultSet.getDouble("price"));
        return vehicle;
    }
}
