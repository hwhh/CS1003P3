import Entitys.Experiment;
import Entitys.Sample;
import Entitys.Spectrometer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Main {


    public static SimpleDateFormat finalFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static EntityManager entity_manager;




    //Basic function which prompts the user to input an integer
    public static int getInputInt()  {
        int input = 0;
        Scanner scanIn = new Scanner(System.in);

        try {
            input = scanIn.nextInt();
        }catch (InputMismatchException e){//Throws exception is user enter anything other than an integer
            scanIn.next();
            System.out.println("Please enter a valid integer!");
        }

        return input;//Rerun the input value when its valid
    }

    //Basic function which prompts the user to input an string
    public static String getInputString() {
        Scanner scanIn = new Scanner(System.in);
        String s = null;
        s =scanIn.nextLine();
        while (s.equals("")) {//Makes sure some text is enetered
            System.out.println("Please enter a valid string...");
            s =scanIn.nextLine();
        };
        return s;
    }

    //Function that takes in a sample ID and try's to find a matching ID in the database
    //If matching sample found the sample object is returned
    public static Sample findSample(){
        int samplerIDInput;
        Sample sample;
        do{
            System.out.println("Please enter the ID of the sample:");
            samplerIDInput=Main.getInputInt();
            sample = entity_manager.find(Sample.class, samplerIDInput);
        }while (sample == null);
        return sample;
    }

    //Function that takes in a experiment ID and try's to find a matching ID in the database
    //If matching sample found the experiment object is returned
    public static Experiment findExperiment(){
        int experimentIDinput;
        Experiment experiment;
        do{
            System.out.println("Please enter the ID of the experiment:");
            experimentIDinput=Main.getInputInt();
            experiment = entity_manager.find(Experiment.class, experimentIDinput);
        }while (experiment == null);
        return experiment;
    }

    //Function that takes in a spectrometer ID and try's to find a matching ID in the database
    public static int findSpectrometer(){
        int spectrometerIDInput;
        Spectrometer spectrometer;
        do{
            System.out.println("Please enter the ID of the spectrometer:");
            spectrometerIDInput=Main.getInputInt();
            spectrometer = entity_manager.find(Spectrometer.class, spectrometerIDInput);
        }while (spectrometer == null);
        return spectrometer.spectrometerID;
    }

    public static void main(String[] args) {
        try {
            Properties.setup();
            // This uses getPropertiesForTableAutoCreation: to create the tables for the first time
            Map<String, String> properties = Properties.getPropertiesForTableAutoCreation();
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("ExperimentService", properties);


            entity_manager = factory.createEntityManager();



            System.out.print("Attempting to add data to database...");
            //Read in the data from the CSV file and add it to the database
            Store.storeObjects(entity_manager, args[0]);
            System.out.print("Done!\n");
            int inputChoice;
            //User interface to allow user to choose what to do, loops until user enters -1
            do{
                System.out.println("\n1. Remove and object from database\n2. Retrieve an object from the database" +
                        "\n3. Retrieve all objects from the database \n4. Update an object in database \n5. Get statistics");
                System.out.println("\nPlease select and option or enter -1 to quit:");
                //Get user input, execute command based on input
                inputChoice=getInputInt();
                switch (inputChoice) {
                    case (1):
                        Remove.removeObjects(entity_manager);
                        break;
                    case (2):
                        Retrieve.retrieveObjects(entity_manager);
                        break;
                    case (3):
                        RetrieveAll.retrieveAllObjects(entity_manager);
                        break;
                    case (4):
                        Update.updateObjects(entity_manager);
                        break;
                    case (5):
                        RetrieveStats.findTimes(entity_manager);
                        RetrieveStats.findNoOfExperiment(entity_manager);
                        int spectrometerIDInput = findSpectrometer();
                        //If user chooses to 'view stats' they must enter the ID of the spectrometer they wish to view the stats for
                        switch (spectrometerIDInput) {
                            case(1):
                                RetrieveStats.findSamplesOnSpec(entity_manager,1 );
                                break;
                            case(2):
                                RetrieveStats.findSamplesOnSpec(entity_manager,2);
                                break;
                            case(3):
                                RetrieveStats.findSamplesOnSpec(entity_manager,3 );
                                break;
                            case(4):
                                RetrieveStats.findSamplesOnSpec(entity_manager,4);
                                break;
                            case(5):
                                RetrieveStats.findSamplesOnSpec(entity_manager,5 );
                                break;
                            case(6):
                                RetrieveStats.findSamplesOnSpec(entity_manager,6);
                                break;
                            case(7):
                                RetrieveStats.findSamplesOnSpec(entity_manager,7);
                                break;
                            default:
                                System.out.println("Please enter a integer between 1 and 7");
                        }
                    default:
                        break;
                }

            }while(inputChoice!=-1);


        } catch (Exception e) {
            System.out.println("ERROR! "+ e.getMessage());
            System.exit(0);
        }
    }
    }
