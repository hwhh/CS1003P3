package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ExperimentReader {


    public  class Experiment{
        String record_submit_time, sample_duration, exp_name, exp_description, solvent_abbr, group_abbr, spectrometer_name;
        int sample_id, sample_holderno, exp_id, user_id, spectrometer_id, spectrometer_capacity;
    }

    public  final String CVS_SPLIT_BY = ",";


    private  BufferedReader bufferedReader;
    private  Experiment nextExperiment = null;
    private  String[] records;
    private  double count = 0;
    private boolean error=false;
    private DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd" );
    private Date date1;


    public ExperimentReader(String inputFileName) throws FileNotFoundException {
        bufferedReader = new BufferedReader(new FileReader(inputFileName));
    }

    //Get the next line of the input file and add contents to array
    private  void findNextExperiment() throws IOException {
        String line = bufferedReader.readLine();
        if (line != null) {
            records = line.split(CVS_SPLIT_BY);
            nextExperiment = new Experiment();
            assignValues(records);
        }

    }
     //Function to format the record_submit_time, to matches SQL DATETIME data type format
    private String formatDate(String input){
        String time, date;
        //Remove the date from the input string
        time=input.substring(11);
        time="00:"+time;
        //Remove the time from the input string
        date=input.substring(0,10);
        try {
            //Attempt to transform the date string to a date object
            date1= originalFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Convert the date back to a string and add the time to the end of the string
        return (targetFormat.format(date1)+" "+time);
    }


    private  void assignValues(String[] records) throws IOException {
        //Ignore the first line of the input file
        if (count == 0){
            count++;
            findNextExperiment();
        }
        else{
            try {


            //Set the correct values from the array to the attributes of the experiment class
            nextExperiment.sample_id= Integer.parseInt(records[0]);
            nextExperiment.record_submit_time = formatDate(records[1]);
            nextExperiment.sample_holderno= Integer.parseInt(records[2]);
            nextExperiment.sample_duration = records[3];
            nextExperiment.exp_id= Integer.parseInt(records[4]);
            nextExperiment.exp_name = records[5];
            nextExperiment.exp_description = records[6];
            nextExperiment.user_id= Integer.parseInt(records[7]);
            nextExperiment.group_abbr = records[8];
            nextExperiment.solvent_abbr = records[9];
            nextExperiment.spectrometer_id= Integer.parseInt(records[10]);
            nextExperiment.spectrometer_name = records[11];
            nextExperiment.spectrometer_capacity= Integer.parseInt(records[12]);
            count++;
            error=false;
            }catch (Exception e){
                if (error) {
                    System.out.println("To many errors in input file... System exiting");
                    System.exit(0);
                } else {
                    error=true;
                    findNextExperiment();
                }
            }
        }
    }

    //Check to see whether there are any more experiments in the input file
    public  boolean hasNextExperiment() throws IOException {
        if (nextExperiment == null)
            findNextExperiment();
        return (nextExperiment != null);
    }

    //Clear the data in the experiments object
    public  Experiment getNextExperiment() {
        Experiment experiment = nextExperiment;
        nextExperiment = null;
        return experiment;
    }



}
