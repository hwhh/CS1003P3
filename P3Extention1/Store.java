import javax.persistence.EntityManager;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Store {


    public static void storeObjects(EntityManager entity_manager, String fileName) {
        entity_manager.getTransaction().begin();
        ExperimentReader experimentReader = null;
        try {
            experimentReader = new ExperimentReader(fileName);
            while (experimentReader.hasNextExperiment()) {
                //Create a new object for each experiment which contains the data for each experiment
                Experiment e = experimentReader.getNextExperiment();
                entity_manager.persist(e);
            }
            entity_manager.getTransaction().commit();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
