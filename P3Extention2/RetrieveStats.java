import Entitys.Experiment;
import Entitys.Sample;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class RetrieveStats {

    public static void findSamplesOnSpec(EntityManager entity_manager, int spectrometerID){
        try {
            //Find all the samples in the database where the spectrometer ID atches that of the input ID
            Query query = entity_manager.createQuery("SELECT s FROM Sample s WHERE SPECTROMETER_ID=" + spectrometerID + "");
            //Add all the returned objects to a list
            List<Sample> samples = query.getResultList();
            //Get and print the name of the spectrometer
            System.out.println("Experiments run on: " + samples.get(0).getSpectrometer().SpectrometerName);
            System.out.println();
            for (Sample sample : samples) {
                //Loop through list printing details of each object in the list
                System.out.println("ID : " + sample.sampleID);
                System.out.println("exp name : " + sample.getExperiment().experimentName);
                System.out.println("solvent abbr : " + sample.solvent);
                System.out.println();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }



   public static void findTimes (EntityManager entity_manager) {

        try {
            //Create new query's which find the maximum, minimum, total and average duration
            Query query = entity_manager.createQuery("SELECT MAX(sample_duration) AS sample_duration FROM Sample ");
            Query query1 = entity_manager.createQuery("SELECT MIN(sample_duration) AS sample_duration FROM Sample ");
            //Convert the sample duration from time to seconds then sum/average, then convert back to time
            Query query2 = entity_manager.createQuery("SELECT SEC_TO_TIME(SUM(TIME_TO_SEC(sample_duration))) FROM Sample ");
            Query query3 = entity_manager.createQuery("SELECT SEC_TO_TIME(AVG(TIME_TO_SEC(sample_duration))) FROM Sample");
            //Print results from quires
            System.out.println("The longest duration is: " + query.getSingleResult().toString());
            System.out.println("The shortest duration is: " + query1.getSingleResult().toString());
            System.out.println("The total duration is: " + query2.getSingleResult().toString());
            System.out.println("The average duration is: " + query3.getSingleResult().toString());
            System.out.println();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }





    public static void findNoOfExperiment (EntityManager entity_manager) {

        try{
            //Take experiment ID in from usr and calculate the number of occurres in the database for input ID
            Experiment experiment= Main.findExperiment();
            Query query = entity_manager.createQuery("SELECT COUNT(EXPERIMENT_ID) AS Occurences FROM Sample WHERE EXPERIMENT_ID ="+experiment.experimentID+"");
            //Print the results
            System.out.println("The number of samples run using the experiment "+experiment.experimentName+" =  " + query.getSingleResult().toString());
            System.out.println("\n");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
