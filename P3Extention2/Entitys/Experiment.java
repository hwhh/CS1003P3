package Entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Experiment{


    @Id //Represents the primary key field
    @Column(name="EXPERIMENT_ID")//Represents the foreign key field
    public int experimentID;
    public String experimentName, experimentDescription;

    ///////////CONSTRUCTORS///////////
    public Experiment() {}

    public Experiment(String experimentName, String experimentDescription, int experimentID) {
        this.experimentName = experimentName;
        this.experimentDescription = experimentDescription;
        this.experimentID = experimentID;
    }

    ///////////GETTERS AND SETTERS///////////
    private int getExperimentID() {return experimentID;}

    private void setExperimentID(int experimentID) {
        this.experimentID = experimentID;
    }

    public String getExperimentName() {
        return experimentName;
    }

    public void setExperimentName(String experimentName) {
        this.experimentName = experimentName;
    }

    public String getExperimentDescription() {
        return experimentDescription;
    }

    public void setExperimentDescription(String description) {
        this.experimentDescription = description;
    }




}