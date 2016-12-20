import javax.persistence.EntityManager;

public class Retrieve {



    public static void retrieveObjects(EntityManager entity_manager) {

        int idInput;
        do {
            System.out.println("Please enter an id number between 1 and 99: ");
            idInput = Main.getInputInt()+80000;
        }while(idInput<80001 || idInput>80199);

        Experiment experiment = entity_manager.find(Experiment.class, idInput);
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
