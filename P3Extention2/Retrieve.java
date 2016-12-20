

import Entitys.Sample;

import javax.persistence.EntityManager;

public class Retrieve {



    public static void retrieveObjects(EntityManager entity_manager) {
        //Find a sample by taking input from user and print details of that sample
        Sample sample=Main.findSample();
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
