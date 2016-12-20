package com.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManipulation {


    public static int findNoOfExperiment (Connection connection, String tableName, String experiment) throws SQLException {
       //Create a new prepared statement
        java.sql.PreparedStatement statement = null;
        try{
            //The statement takes in the experiment name
            statement= connection.prepareStatement("SELECT COUNT(exp_name) AS Occurences FROM "+ tableName+ "WHERE exp_name = (?);");
            //Get and return the the result of the string
            statement.setString(1, experiment);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
            //close the statement when it is complete
        }finally {
            if (statement != null) statement.close();
        }

    }


    public static void findTimes (Connection connection, String tableName) throws SQLException {
        PreparedStatement statementMax= null;
        PreparedStatement statementMin= null;
        PreparedStatement statementTotal= null;
        try{
            //Create new statements which find the maximum, minimum and total duration
            statementMax = connection.prepareStatement("SELECT MAX(sample_duration) AS sample_duration FROM " + tableName + ";");
            statementMin= connection.prepareStatement("SELECT MIN(sample_duration) AS sample_duration FROM "+tableName+";");
            statementTotal= connection.prepareStatement("SELECT SEC_TO_TIME(SUM(TIME_TO_SEC(sample_duration))) FROM "+tableName+";");
            //Execute the query's
            ResultSet resultSetMax = statementMax.executeQuery();
            ResultSet resultSetMin = statementMin.executeQuery();
            ResultSet resultSetTotal = statementTotal.executeQuery();
            //Get the result from the query's
            resultSetMax.next();
            resultSetMin.next();
            resultSetTotal.next();
            //Convert results to strings and print them
            String maxTime = resultSetMax.getString(1);
            String minTime = resultSetMin.getString(1);
            String totalDuration = resultSetTotal.getString(1);
            System.out.println("The longest duration is: "+maxTime);
            System.out.println("The shortest duration is: "+minTime);
            System.out.println("The total duration is: "+totalDuration);
            //Close the query statements
        }finally {
            if (statementMax != null) statementMax.close();
            if (statementMin != null) statementMin.close();
            if (statementTotal != null) statementTotal.close();
        }

    }

    public static void findSamplesOnSpec(Connection connection, String tableName, String spectrometer) throws SQLException {
        java.sql.PreparedStatement statement = null;
        try {
            //Create new statements which finds the experiments that where preformed on a spectrometer
            statement = connection.prepareStatement("SELECT sample_id, solvent_abbr, exp_name FROM " + tableName + " WHERE  spectrometer_name= (?);");
            //Set the query to take input from user
            statement.setString(1, spectrometer);
            //Execute query and return then print the results
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int sample_id = resultSet.getInt(1);
                String exp_name = resultSet.getString(2);
                String solvent_abbr = resultSet.getString(3);
                System.out.println("ID : " + sample_id);
                System.out.println("exp name : " + exp_name);
                System.out.println("solvent abbr : " + solvent_abbr);
                System.out.println();
            }
            //Close the query statement
        } finally {
            if (statement != null) statement.close();
        }
    }
}

