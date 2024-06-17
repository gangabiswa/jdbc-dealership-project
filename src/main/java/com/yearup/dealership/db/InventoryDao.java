package com.yearup.dealership.db;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryDao {
    private DataSource dataSource;

    public InventoryDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicleToInventory(String vin, int dealershipId) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO inventory (vin, dealership_id) VALUES (?, ?)")) {

            preparedStatement.setString(1, vin);
            preparedStatement.setInt(2, dealershipId);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Vehicle added to inventory successfully.");

                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int inventoryId = generatedKeys.getInt(1);
                        System.out.println("New Inventory ID: " + inventoryId);
                    }
                }
            } else {
                System.out.println("Failed to add vehicle to inventory.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // TODO: Implement the logic to add a vehicle to the inventory
    }

    public void removeVehicleFromInventory(String vin) {
        // TODO: Implement the logic to remove a vehicle from the inventory


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
            ex.printStackTrace();
        }

    }
}
