import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Main {


    public static SimpleDateFormat finalFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


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

    public static String getInputString() {
        Scanner scanIn = new Scanner(System.in);
        return scanIn.nextLine();
    }



    public static void main(String[] args) {
        try {
            Properties.setup();

            Map<String, String> properties = Properties.getPropertiesForTableAutoCreation();

            EntityManagerFactory factory = Persistence.createEntityManagerFactory("ExperimentService", properties);
            EntityManager entity_manager = factory.createEntityManager();

            System.out.print("Adding data to database...");
            Store.storeObjects(entity_manager, args[0]);
            System.out.println("Done\n");

            int inputChoice;
            System.out.println("1. Remove and object from database\n2. Retrieve and object from database" +
                    "\n3. Retrieve all objects from database \n4. Update an object in database");
            do{
                System.out.println("\nPlease select and option or enter -1 to quit:");
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
                        RetrieveStats.findSamplesOnSpec(entity_manager);
                        RetrieveStats.findTimes(entity_manager);
                        RetrieveStats.findNoOfExperiment(entity_manager);
                        break;
                }

            }while(inputChoice!=-1);


        } catch (Exception e) {
            System.out.println(e.getCause());
        }
    }
    }
