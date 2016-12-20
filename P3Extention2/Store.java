import Entitys.Sample;
import org.hibernate.*;


import javax.persistence.EntityManager;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Store {


    public static void storeObjects(EntityManager entity_manager, String fileName) {
        entity_manager.getTransaction().begin();
        CSVReader CSVReader = null;
        try {
            int i =0;
            CSVReader = new CSVReader(fileName);
            while (CSVReader.hasNextSample()) {
                //Create a new object for each experiment which contains the data for each experiment
                Sample sample = CSVReader.getNextSample();
                entity_manager.persist(sample);
                if ( i % 20 == 0 ) { //20, same as the JDBC batch size
                    //flush a batch of inserts and release memory:
                     entity_manager.flush();
                    entity_manager.clear();
                }
                i++;


            }
            entity_manager.getTransaction().commit();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR! "+ e.getMessage());
            System.exit(0);
        } catch (IOException e) {
            System.out.println("ERROR! "+ e.getMessage());
            System.exit(0);
        }

    }

}