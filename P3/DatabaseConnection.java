package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {



    public static Connection setUp() throws SQLException {
        //Attempt to connect to the database using the properties generated in the 'DatabaseProperties' class
        try {Connection connection = DriverManager.getConnection(DatabaseProperties.getURL(), DatabaseProperties.username, DatabaseProperties.password);
            return connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void createTable(Connection connection, String type) throws SQLException {
       //Try with resources, statement automatically closes
        try (Statement statement = connection.createStatement()){
            //Delete any exsisting tables with same name
            statement.executeUpdate("DROP TABLE IF EXISTS Practical3");
            //Create new table
            statement.executeUpdate("CREATE TABLE Practical3 (sample_id int, record_submit_time "+type+", " +
                    "sample_holderno int, sample_duration TIME, exp_id int, exp_name VARCHAR(40), exp_description VARCHAR(60)," +
                    "user_id int, group_abbr VARCHAR(10), solvent_abbr VARCHAR (10), spectrometer_id int, spectrometer_name VARCHAR(8)," +
                    "spectrometer_capacity int)");
        }
    }

}












