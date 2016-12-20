package Entitys;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
public class Sample {

    @Id//Represents the primary key field
    @Column(name="SAMPLE_ID")
    public int sampleID;
    public int holder_no;
    public java.util.Date record_submit_time;
    public Time sample_duration;
    public String solvent;

    @ManyToOne//unidirectional One-to-Many relationship
    @JoinColumn(name ="SPECTROMETER_ID")
    private Spectrometer spectrometer;

    @ManyToOne//unidirectional One-to-Many relationship
    @JoinColumn(name ="EXPERIMENT_ID")
    private Experiment experiment;


    @ManyToOne//unidirectional One-to-Many relationship
    @JoinColumn(name ="USER_ID")
    private User user;


    ///////////CONSTRUCTOR///////////
    public Sample() {}

    ///////////GETTERS AND SETTERS///////////
    private int getSampleID() {
        return sampleID;
    }

    private void setSampleID(int sampleID) {
        this.sampleID = sampleID;
    }

    public int getHolder_no() {
        return holder_no;
    }

    public void setHolder_no(int holder_no) {
        this.holder_no = holder_no;
    }

    public Date getRecord_submit_time() {
        return record_submit_time;
    }

    public void setRecord_submit_time(Date record_submit_time) {this.record_submit_time = record_submit_time;}

    public Time getSample_duration() {
        return sample_duration;
    }

    public void setSample_duration(Time sample_duration) {
        this.sample_duration = sample_duration;
    }

    public String getSolvent() {
        return solvent;
    }

    public void setSolvent(String solvent) {
        this.solvent = solvent;
    }

    public Spectrometer getSpectrometer() {
        return spectrometer;
    }

    public void setSpectrometer(Spectrometer spectrometer) {
        this.spectrometer = spectrometer;
    }

    public Experiment getExperiment() {
        return experiment;
    }

    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
