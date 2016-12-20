import Entitys.Experiment;
import Entitys.Sample;
import Entitys.Spectrometer;
import Entitys.User;

import javax.persistence.EntityManager;
import java.lang.reflect.Method;
import java.sql.Time;

public class Update {



    //This method prints all the public getters from the classes in the 'Entitys' package
    //(Excluding class getters)
    public static void getGetters(Class className){
        int i =1;
        //Loop for every method in the input class
        for (Method m : className.getMethods()) {
           //Check method is getter method
            if (m.getName().contains("get") && m.getReturnType()!=Experiment.class && m.getReturnType()!=User.class && m.getReturnType()!=Spectrometer.class) {
                //Remove all non letter characters
                String substring = m.getName().replaceAll("[^a-z^A-Z ]", " ");
                //Make sure the method is not a class getter
                if (!substring.substring(3).equals("Class")) {
                    System.out.println(i + ". " + substring.substring(3));
                    i++;
                }
            }
        }
    }



    public static void updateObjects(EntityManager entity_manager) {
        try {

            entity_manager.getTransaction().begin();
            //Find a sample by taking input from user
            Sample sample=Main.findSample();
            //Get the Experiment, Spectrometer and user for the sample
            Experiment experiment = sample.getExperiment();
            Spectrometer spectrometer = sample.getSpectrometer();
            User user = sample.getUser();
            //Print the getters
            getGetters(Experiment.class);
            getGetters(Sample.class);
            getGetters(Spectrometer.class);
            getGetters(User.class);
            //User interface to allow user to choose what to do
            int getterInput;
            do{
                System.out.println("Please choose a property you wish to update: ");
                getterInput=Main.getInputInt();
            }while(getterInput<1 || getterInput>13);//Ensures valid choice is made

            //Get user input, execute command based on input
            switch (getterInput){
                case(1):
                    System.out.println("Please enter a new experiment name:");
                    experiment.setExperimentName(Main.getInputString());
                    break;
                case(2):
                    System.out.println("Please enter a new experiment description:");
                    experiment.setExperimentDescription(Main.getInputString());
                    break;
                case(3):
                    System.out.println("Please enter a new holder number:");
                    sample.setHolder_no(Main.getInputInt());
                    break;
                case(4):
                    do {//Permanent lop until break command is reached

                        try {
                            System.out.println("Please enter a valid time and date in the format YYYY-MM-DD HH:MM:SS");
                            //Attempt to parse the input date
                            sample.setRecord_submit_time(Main.finalFormat.parse(Main.getInputString()));
                            break;
                        } catch (Exception e1) {//If the date input is not in the correct format user is required to re enter date
                            System.out.println("Invalid input");
                        }
                    }while (true);
                    break;
                case(5):
                    do {//Permanent lop until break command is reached
                        try {
                            System.out.println("Please enter a valid time HH:MM:SS");
                            //Attempt to parse the input time
                            sample.setSample_duration(Time.valueOf(Main.getInputString()));
                            break;
                        } catch (Exception e1) {//If the time input is not in the correct format user is required to re enter time
                            System.out.println("Invalid input");
                        }
                    }while (true);
                    break;
                case(6):
                    System.out.println("Please enter a new solvent abbreviation:");
                    sample.setSolvent(Main.getInputString());
                    break;
                case(7):
                    int spectrometerIDInput;
                    do{//Loop until valid spectrometer ID entered
                        System.out.println("Please enter a new spectrometer id:");
                        spectrometerIDInput=Main.getInputInt();
                        spectrometer= entity_manager.find(Spectrometer.class, spectrometerIDInput);//Check to see if spectrometer ID is valid
                    }while (spectrometer == null);
                        spectrometer.setSpectrometerID(spectrometerIDInput);
                        sample.setSpectrometer(spectrometer);
                    break;
                case(8):
                    int UserIDInput;
                    do{//Loop until valid user ID entered
                        System.out.println("Please enter a new user id:");
                        UserIDInput=Main.getInputInt();
                        user= entity_manager.find(User.class, UserIDInput);//Check to see if user ID is valid
                    }while (spectrometer == null);
                    user.setUserID(UserIDInput);
                    sample.setUser(user);
                    break;
                case(9):
                    System.out.println("Please enter a new group abbreviation:");
                    user.setGroupName(Main.getInputString());
                    break;
                default:
                    System.out.println("Please enter a valid choice");



            }
            System.out.println("Update done!\n");
            entity_manager.getTransaction().commit();
        } catch (Exception e1) {
            e1.getStackTrace();
        }

    }
}














