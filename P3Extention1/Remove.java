import javax.persistence.EntityManager;


public class Remove {





    public static void removeObjects(EntityManager entity_manager){
        entity_manager.getTransaction().begin();
        int idInput;
        do {
            System.out.println("Please enter an id number between 1 and 99: ");
            idInput = Main.getInputInt()+80000;
        }while(idInput<80001 || idInput>80199);
        Experiment experiment = entity_manager.find(Experiment.class, idInput);
        System.out.println("Experiment with ID : " + experiment.id+" has now been removed!");
        entity_manager.remove(experiment);
        entity_manager.getTransaction().commit();
    }
}
