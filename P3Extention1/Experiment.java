import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;

@Entity
public class Experiment{

    @Id //Represents the primary key field
    public int id;
    public Time sample_duration;
    public java.util.Date record_submit_time;
    public String exp_name, exp_description, solvent_abbr, group_abbr, spectrometer_name;
    public int sample_holderno, exp_id, user_id, spectrometer_id, spectrometer_capacity;

    public Experiment() {
    }

    private int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id; }

    public java.util.Date getRecord_submit_time() {
        return record_submit_time;
    }

    public void setRecord_submit_time(java.util.Date record_submit_time) {
        this.record_submit_time = record_submit_time;
    }

    public Time getSample_duration() {
        return sample_duration;
    }

    public void setSample_duration(Time sample_duration) {
        this.sample_duration = sample_duration;
    }

    public String getExp_name() {
        return exp_name;
    }

    public void setExp_name(String exp_name) {
        this.exp_name = exp_name;
    }

    public String getExp_description() {
        return exp_description;
    }

    public void setExp_description(String exp_description) {
        this.exp_description = exp_description;
    }

    public String getSolvent_abbr() {
        return solvent_abbr;
    }

    public void setSolvent_abbr(String solvent_abbr) {
        this.solvent_abbr = solvent_abbr;
    }

    public String getGroup_abbr() {
        return group_abbr;
    }

    public void setGroup_abbr(String group_abbr) {
        this.group_abbr = group_abbr;
    }

    public String getSpectrometer_name() {
        return spectrometer_name;
    }

    public void setSpectrometer_name(String spectrometer_name) {
        this.spectrometer_name = spectrometer_name;
    }

    public int getSample_holderno() {
        return sample_holderno;
    }

    public void setSample_holderno(int sample_holderno) {
        this.sample_holderno = sample_holderno;
    }

    private int getExp_id() {
        return exp_id;
    }

    private void setExp_id(int exp_id) {
        this.exp_id = exp_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getSpectrometer_id() {
        return spectrometer_id;
    }

    public void setSpectrometer_id(int spectrometer_id) {
        this.spectrometer_id = spectrometer_id;
    }

    public int getSpectrometer_capacity() {
        return spectrometer_capacity;
    }

    public void setSpectrometer_capacity(int spectrometer_capacity) {
        this.spectrometer_capacity = spectrometer_capacity;
    }
}