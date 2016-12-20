import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


public class RetrieveAll {



    public static void retrieveAllObjects(EntityManager entity_manager) {
        Query query = entity_manager.createQuery("SELECT e FROM Experiment e");
        List<Experiment> experiments = query.getResultList();
        for (Experiment experiment : experiments) {
            System.out.println("Next Experiment:");
            System.out.println("ID : " + experiment.id);
            System.out.println("record submit time : " +  experiment.record_submit_time);
            System.out.println("sample holderno : " +  experiment.sample_holderno);
            System.out.println("sample duration : " +  experiment.sample_duration);
            System.out.println("exp id : " +  experiment.exp_id);
            System.out.println("exp name : " +  experiment.exp_name);
            System.out.println("exp description : " +  experiment.exp_description);
            System.out.println("user id : " +  experiment.user_id);
            System.out.println("group abbr : " +  experiment.group_abbr);
            System.out.println("solvent abbr : " +  experiment.solvent_abbr);
            System.out.println("spectrometer id : " +  experiment.spectrometer_id);
            System.out.println("spectrometer name : " +  experiment.spectrometer_name);
            System.out.println("spectrometer capacity : " +  experiment.spectrometer_capacity);
            System.out.println();
        }

    }




}
