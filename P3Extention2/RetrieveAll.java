import Entitys.Sample;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


public class RetrieveAll {



    public static void retrieveAllObjects(EntityManager entity_manager) {
        //Get all the items in the database
        Query query = entity_manager.createQuery("SELECT s FROM Sample s");
        //Add all the returned objects to a list
        List<Sample> samples = query.getResultList();
        for (Sample sample : samples) {
            //Loop through list printing details of each object in the list
            System.out.println("Next Experiment:");
            System.out.println("ID : " + sample.sampleID);
            System.out.println("record submit time : " +  sample.record_submit_time);
            System.out.println("sample holderno : " +  sample.holder_no);
            System.out.println("sample duration : " +  sample.sample_duration);
            System.out.println("exp id : " +  sample.getExperiment().experimentID);
            System.out.println("exp name : " +  sample.getExperiment().experimentName);
            System.out.println("exp description : " +  sample.getExperiment().experimentDescription);
            System.out.println("user id : " +  sample.getUser().userID);
            System.out.println("group abbr : " +  sample.getUser().groupName);
            System.out.println("solvent abbr : " +  sample.solvent);
            System.out.println("spectrometer id : " +  sample.getSpectrometer().spectrometerID);
            System.out.println("spectrometer name : " +  sample.getSpectrometer().SpectrometerName);
            System.out.println("spectrometer capacity : " +  sample.getSpectrometer().capacity);
            System.out.println();
        }

    }




}
