package com.company;

import java.io.IOException;
import java.sql.SQLException;

public class DatabaseProperties {

    public static String host, port, username, password,db;



    public static void getProperties() throws IOException {
        //get the properties from the properties file


        host = Main.properties.getProperty("host");
        port = Main.properties.getProperty("port");
        username = Main.properties.getProperty("username");
        password = Main.properties.getProperty("password");
        db = Main.properties.getProperty("db");

        if (host==null || port==null || username==null || db==null || getURL()==null){
            System.out.println("Bad properties file");
            System.exit(0);
        }
        try {
            DatabaseConnection.setUp();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getURL (){
        //Create a new string with the url of database
        StringBuilder builder = new StringBuilder();
        builder.append("jdbc:mysql://");
        builder.append(host);
        builder.append(":");
        builder.append(port);
        builder.append("/");
        builder.append(db);
        return builder.toString();

    }
}
