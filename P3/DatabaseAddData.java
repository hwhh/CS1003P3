package com.company;


import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseAddData {


    public static void addEntity(Connection connection, String tableName, ExperimentReader.Experiment experiment) throws SQLException {
        //New prepared statement that is used to add the data to the database
        java.sql.PreparedStatement statement = connection.prepareStatement("INSERT INTO "+tableName+" VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        //Take the attributes from the input experiment and add the data to the database
        statement.setInt(1, experiment.sample_id);
        statement.setString(2, experiment.record_submit_time);
        statement.setInt(3, experiment.sample_holderno);
        statement.setString(4, experiment.sample_duration);
        statement.setInt(5, experiment.exp_id);
        statement.setString(6, experiment.exp_name);
        statement.setString(7, experiment.exp_description);
        statement.setInt(8, experiment.user_id);
        statement.setString(9, experiment.group_abbr);
        statement.setString(10, experiment.solvent_abbr);
        statement.setInt(11, experiment.spectrometer_id);
        statement.setString(12, experiment.spectrometer_name);
        statement.setInt(13, experiment.spectrometer_capacity);
        statement.execute();




    }


    public static void printTable(Connection connection, String table) throws SQLException {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            //Statement that will return all the data in the database
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + table);

            System.out.println("\ncontents of table:");
            //While there are rows which fulfill the requirements of the SQL query
            while (resultSet.next()) {
                //Get the data from the current row and print it
                int sample_id = resultSet.getInt(1);
                String record_submit_time = resultSet.getString(2);
                int sample_holderno = resultSet.getInt(3);
                String sample_duration = resultSet.getString(4);
                int exp_id = resultSet.getInt(5);
                String exp_name = resultSet.getString(6);
                String exp_description = resultSet.getString(7);
                int user_id = resultSet.getInt(8);
                String group_abbr = resultSet.getString(9);
                String solvent_abbr = resultSet.getString(10);
                int spectrometer_id = resultSet.getInt(11);
                String spectrometer_name = resultSet.getString(12);
                int spectrometer_capacity = resultSet.getInt(13);
                System.out.println("ID : " + sample_id);
                System.out.println("record submit time : " + record_submit_time);
                System.out.println("sample holderno : " + sample_holderno);
                System.out.println("sample duration : " + sample_duration);
                System.out.println("exp id : " + exp_id);
                System.out.println("exp name : " + exp_name);
                System.out.println("exp description : " + exp_description);
                System.out.println("user id : " + user_id);
                System.out.println("group abbr : " + group_abbr);
                System.out.println("solvent abbr : " + solvent_abbr);
                System.out.println("spectrometer id : " + spectrometer_id);
                System.out.println("spectrometer name : " + spectrometer_name);
                System.out.println("spectrometer capacity : " + spectrometer_capacity);

                System.out.println();
            }
            try {
                //These method calls with print the statistics
                DatabaseManipulation.findSamplesOnSpec(connection, table, "Alec");
                DatabaseManipulation.findTimes(connection, table);
                System.out.println("The number of samples run using the experiment “proton.a.and” = "+DatabaseManipulation.findNoOfExperiment(connection, table, "proton.a.and"));
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
            //close the statement
        } finally {
            if (statement != null) statement.close();
        }
    }
}
