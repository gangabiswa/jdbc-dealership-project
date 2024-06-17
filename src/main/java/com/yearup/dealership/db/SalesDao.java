package com.yearup.dealership.db;

import com.yearup.dealership.models.SalesContract;
import jdk.internal.icu.text.UTF16;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class SalesDao {
    private DataSource dataSource;

    public SalesDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addSalesContract(SalesContract salesContract) {
        // TODO: Implement the logic to add a sales contract

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO sales_contracts (vin, sale_date, price) " +
                             "VALUES (?, ?, ?)")) {

            preparedStatement.setString(1, salesContract.getVin());
            preparedStatement.setDate(2, Date.valueOf(salesContract.getSaleDate()));
            preparedStatement.setDouble(3, salesContract.getPrice());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Sales contract added successfully.");
            } else {
                System.out.println("Failed to add sales contract.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static class Date {
        public static java.sql.Date valueOf(LocalDate saleDate) {
            return null;
        }
    }
}
