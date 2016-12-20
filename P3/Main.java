package com.company;

import java.io.FileInputStream;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

public class Main {


    public static Properties properties;
    public static final String TABLE_NAME = "Practical3";

    //Basic function which prompts the user to input an integer
    public static int getInputInt()  {
        int input = 0;
        Scanner scanIn = new Scanner(System.in);
        //User input an integer before the function will end
        try {
            input = scanIn.nextInt();
        }catch (InputMismatchException e){//Throws exception is user enter anything other than an integer
            scanIn.next();
            System.out.println("Please enter a valid integer!");
        }

        return input;//Rerun the input value when its valid
    }

    public static void main(String[] args) {
        //Check to see the correct number of program arguments have been entered
        if (args.length ==2) {
            //New file stream object, which is automatically closed
            try (FileInputStream propInputStream = new FileInputStream(args[0])){

                //Set up the database connection properties and the connect to the database
                properties = new Properties();
                properties.load(propInputStream);
                DatabaseProperties.getProperties();
                Connection connection = DatabaseConnection.setUp();
                System.out.println("1. Load small data file in using method 1");
                System.out.println("2. Load large data file in using method 2");
                System.out.println("Please choose an option:");
                int inputChoice =getInputInt();
                //User interface to allow user to choose what to do
                switch (inputChoice){
                    case(1):
                        //Add the data using an SQL INSERT method (SLOW) - more control
                        DatabaseConnection.createTable(connection, "DATETIME");
                        getData(args, connection);
                        break;
                    case (2):
                        //Add the data using LOAD DATA LOCAL INFILE method (QUICK) - not as much control
                        DatabaseConnection.createTable(connection, "VARCHAR(20)");
                        assert connection != null;
                        java.sql.PreparedStatement statement1 = connection.prepareStatement("LOAD DATA LOCAL INFILE 'data-small.csv'\n" +
                                "INTO TABLE Practical3 \n" +
                                "FIELDS TERMINATED BY ',' \n" +
                                "LINES TERMINATED BY '\\n' " +
                                "(sample_id , record_submit_time, sample_holderno, sample_duration, exp_id, " +
                                "exp_name, exp_description ,user_id , group_abbr, solvent_abbr, spectrometer_id, " +
                                "spectrometer_name, spectrometer_capacity);");
                        statement1.execute();
                        break;
                    default:
                        System.out.println("Invalid input");
                }





            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

    public static void getData (String[] args, Connection connection) throws IOException, SQLException {
        ExperimentReader experimentReader = new ExperimentReader(args[1]);
        while (experimentReader.hasNextExperiment()) {
            //Create a new object for each experiment which contains the data for each experiment
            ExperimentReader.Experiment e = experimentReader.getNextExperiment();
            DatabaseAddData.addEntity(connection,TABLE_NAME,e);
        }
        DatabaseAddData.printTable(connection,TABLE_NAME);
        connection.close();

    }
}
