package Entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Spectrometer {

    @Id//Represents the primary key field
    @Column(name="SPECTROMETER_ID")//Represents the foreign key field
    public int spectrometerID;
    public int capacity;
    public String SpectrometerName;



    ///////////CONSTRUCTORS///////////
    public Spectrometer() {}

    public Spectrometer(int spectrometerID, int capacity, String name) {
        this.spectrometerID = spectrometerID;
        this.capacity = capacity;
        this.SpectrometerName = name;
    }

    ///////////GETTERS AND SETTERS///////////
    public int getSpectrometerID() {return spectrometerID;}

    public void setSpectrometerID(int spectrometerID) {
        this.spectrometerID = spectrometerID;
    }

    private int getCapacity() {
        return capacity;
    }

    private void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    private String getSpectrometerName() {
        return SpectrometerName;
    }

    private void setSpectrometerName(String spectrometerName) {
        SpectrometerName = spectrometerName;
    }
}
