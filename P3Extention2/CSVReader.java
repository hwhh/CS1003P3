import Entitys.Experiment;
import Entitys.Sample;
import Entitys.Spectrometer;
import Entitys.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class CSVReader {

    public  final String CVS_SPLIT_BY = ",";

    private BufferedReader bufferedReader;
    private Sample nextSample = null;



    private  double count = 0;

    private DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
    private Date date1;
    private boolean error = false;

        public CSVReader(String inputFileName) throws FileNotFoundException {
            bufferedReader = new BufferedReader(new FileReader(inputFileName));
        }

        //Get the next line of the input file and add contents to array
        private  void findNextSample() throws IOException {
            String line = bufferedReader.readLine();
            if (line != null) {
                String[] records = line.split(CVS_SPLIT_BY);
                nextSample = new Sample();
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
                return "";
            }
            //Convert the date back to a string and add the time to the end of the string
            return (targetFormat.format(date1)+" "+time);
        }


        private  void assignValues(String[] records) throws IOException {
            //Ignore the first line of the input file
            if (count == 0){
                count++;
                findNextSample();
            }
            else{

                try {
                    /*find previous entry’s in the database with mating key values as those read in from
                    the CSV file. If there are no matches found the program uses the values read in from
                    the CSV file to initialise the new object and the object is added to the database. */
                    Experiment experiment = Main.entity_manager.find(Experiment.class, Integer.parseInt(records[4]));
                    if(experiment == null) {
                        experiment = new Experiment(records[5],records[6],Integer.parseInt(records[4]));
                        Main.entity_manager.persist(experiment);
                    }

                     Spectrometer spectrometer= Main.entity_manager.find(Spectrometer.class, Integer.parseInt(records[10]));
                    if(spectrometer == null) {
                        spectrometer = new Spectrometer(Integer.parseInt(records[10]),Integer.parseInt(records[12]),records[11]);
                        Main.entity_manager.persist(spectrometer);
                    }

                    User user = Main.entity_manager.find(User.class, Integer.parseInt(records[7]));
                    if(user == null) {
                        user = new User(Integer.parseInt(records[7]),records[8]);
                        Main.entity_manager.persist(user);
                    }
                    //Set the correct values from the array to the attributes of the sample class
                    nextSample.sampleID = Integer.parseInt(records[0]);
                    nextSample.record_submit_time = Main.finalFormat.parse(formatDate(records[1]));
                    nextSample.sample_duration = Time.valueOf(records[3]);
                    nextSample.holder_no = Integer.parseInt(records[2]);
                    nextSample.solvent = records[9];
                    nextSample.setExperiment(experiment);
                    nextSample.setSpectrometer(spectrometer);
                    nextSample.setUser(user);
                    count++;
                    error=false;
                }catch (Exception e){
                    if (error) {
                        System.out.println("To many errors in input file... System exiting");
                        System.exit(0);
                    } else {
                        error=true;
                        findNextSample();

                    }
                }
            }
        }

        //Check to see whether there are any more experiments in the input file
        public  boolean hasNextSample() throws IOException {
            if (nextSample == null)
                findNextSample();
            return (nextSample != null);
        }

        //Clear the data in the experiments object
        public  Sample getNextSample() {
            Sample sample = nextSample;
            nextSample = null;
            return sample;
        }

}

