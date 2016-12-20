import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ExperimentReader {



        public  final String CVS_SPLIT_BY = ",";


        private  BufferedReader bufferedReader;
        private  Experiment nextExperiment = null;
        private  String[] records;
        private  double count = 0;

        private DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy");
        private SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
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

        private String formatDate(String input){
            String time, date;
            time=input.substring(11);
            time="00:"+time;
            date=input.substring(0,10);
            try {
                date1= originalFormat.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return (targetFormat.format(date1)+" "+time);
        }


        private  void assignValues(String[] records) throws IOException {
            //Ignore the first line of the input file
            if (count == 0){
                count++;
                findNextExperiment();
            }
            else{
                //Set the correct values from the array to the attributes of the experiment class
                try {
                    nextExperiment.id = Integer.parseInt(records[0]);
                    nextExperiment.record_submit_time = Main.finalFormat.parse(formatDate(records[1]));
                    nextExperiment.sample_holderno = Integer.parseInt(records[2]);
                    nextExperiment.sample_duration = Time.valueOf(records[3]);
                    nextExperiment.exp_id = Integer.parseInt(records[4]);
                    nextExperiment.exp_name = records[5];
                    nextExperiment.exp_description = records[6];
                    nextExperiment.user_id = Integer.parseInt(records[7]);
                    nextExperiment.group_abbr = records[8];
                    nextExperiment.solvent_abbr = records[9];
                    nextExperiment.spectrometer_id = Integer.parseInt(records[10]);
                    nextExperiment.spectrometer_name = records[11];
                    nextExperiment.spectrometer_capacity = Integer.parseInt(records[12]);
                    count++;
                }catch (Exception e){
                    System.out.println(e.getMessage());
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

