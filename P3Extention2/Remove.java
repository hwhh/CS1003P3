
import Entitys.Sample;

import javax.persistence.EntityManager;


public class Remove {



    public static void removeObjects(EntityManager entity_manager){
        //This will remove an Experiment from the database, by getting the ID of the experiment
        entity_manager.getTransaction().begin();
        Sample sample=Main.findSample();
        System.out.println("Experiment with ID : " + sample.sampleID+" has now been removed!");
        entity_manager.remove(sample);
        entity_manager.getTransaction().commit();//Commit changes to database
    }
}
