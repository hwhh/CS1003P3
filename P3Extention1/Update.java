import javax.persistence.EntityManager;
import java.lang.reflect.Method;
import java.sql.Time;





public class Update {


    public static void updateObjects(EntityManager entity_manager) {
        try {
            entity_manager.getTransaction().begin();
            int idInput;
            do {
                System.out.println("Please enter an id number between 1 and 99: ");
                idInput = Main.getInputInt()+80000;
            }while(idInput<80001 || idInput>80199);
            Experiment e = entity_manager.find(Experiment.class, idInput);
            int i = 1;
            for (Method m : Experiment.class.getMethods()) {
                if (m.getName().contains("get")) {
                    String substring = m.getName().replaceAll("[^a-z^A-Z ]", " ");
                    if (!substring.substring(3).equals("Class"))
                        System.out.println(i + ". " + substring.substring(3));
                    i++;
                }
            }
            int getterInput;
            do{
                System.out.println("Please choose a property you wish to update: ");
                getterInput=Main.getInputInt();
            }while(getterInput<1 || getterInput>13);

            switch (getterInput){
                case(1):
                    do {
                        try {
                            System.out.println("Please enter a valid time and date in the format YYYY-MM-DD HH:MM:SS");
                            e.setRecord_submit_time(Main.finalFormat.parse(Main.getInputString()));
                            break;
                        } catch (Exception e1) {
                            System.out.println("Invalid input");
                        }
                    }while (true);
                case(2):
                    do {
                        try {
                            System.out.println("Please enter a valid time HH:MM:SS");
                            e.setSample_duration(Time.valueOf(Main.getInputString()));
                            break;
                        } catch (Exception e1) {
                            System.out.println("Invalid input");
                        }
                    }while (true);
                case(3):
                    System.out.println("Please enter a new experiment name:");
                    e.setExp_name(Main.getInputString());
                    break;
                case(4):
                    System.out.println("Please enter a new experiment description:");
                    e.setExp_description(Main.getInputString());
                    break;
                case(5):
                    System.out.println("Please enter a new solvent abbreviation:");
                    e.setSolvent_abbr(Main.getInputString());
                    break;
                case(6):
                    System.out.println("Please enter a new group abbreviation:");
                    e.setGroup_abbr(Main.getInputString());
                    break;
                case(7):
                    System.out.println("Please enter a new spectrometer name:");
                    e.setSpectrometer_name(Main.getInputString());
                    break;
                case(8):
                    System.out.println("Please enter a new holder number:");
                    e.setSample_holderno(Main.getInputInt());
                    break;
                case(9):
                    System.out.println("Please enter a new user id:");
                    e.setUser_id(Main.getInputInt());
                    break;
                case(10):
                    System.out.println("Please enter a new spectrometer id:");
                    e.setSpectrometer_id(Main.getInputInt());
                    break;
                case(11):
                    System.out.println("Please enter a new spectrometer id:");
                    e.setSpectrometer_id(Main.getInputInt());
                    break;
                case(12):
                    System.out.println("Please enter a new spectrometer capacity:");
                    e.setSpectrometer_capacity(Main.getInputInt());
                    break;
                default:
                    System.out.println("Please enter a valid choice");
            }
            //entity_manager.getTransaction().commit();
        } catch (Exception e1) {
            e1.getStackTrace();
            //System.out.println(e1.getMessage());
        }

    }
}
