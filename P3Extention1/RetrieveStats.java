import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class RetrieveStats {

    public static void findSamplesOnSpec (EntityManager entity_manager) {
        Query query = entity_manager.createQuery("SELECT e FROM Experiment e WHERE spectrometer_name='Alec'");
        System.out.println("Experiments run on 'Alec'");
        List<Experiment> experiments = query.getResultList();
        for (Experiment experiment : experiments) {
            System.out.println("ID : " + experiment.id);
            System.out.println("exp name : " + experiment.exp_name);
            System.out.println("solvent abbr : " + experiment.solvent_abbr);
            System.out.println();
        }
    }

    public static void findTimes (EntityManager entity_manager) {

        try {
            Query query = entity_manager.createQuery("SELECT MAX(sample_duration) AS sample_duration FROM Experiment");
            Query query1 = entity_manager.createQuery("SELECT MIN(sample_duration) AS sample_duration FROM Experiment");
            Query query2 = entity_manager.createQuery("SELECT SEC_TO_TIME(SUM(TIME_TO_SEC(sample_duration))) FROM Experiment");
            System.out.println("The longest duration is: " + query.getSingleResult().toString());
            System.out.println("The shortest duration is: " + query1.getSingleResult().toString());
            System.out.println("The total duration is: " + query2.getSingleResult().toString());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void findNoOfExperiment (EntityManager entity_manager) {

        try{
            Query query = entity_manager.createQuery("SELECT COUNT(exp_name) AS Occurences FROM Experiment WHERE exp_name = 'proton.a.and'");
            System.out.println("The number of samples run using the experiment “proton.a.and” =  " + query.getSingleResult().toString());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
