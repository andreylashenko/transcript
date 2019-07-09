package teamlead.transcript.domain;


import javax.persistence.*;

@Entity
public class LeadZvon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;
    private String operatorName;
    private int operatorExtension;
    private String leadName;
    private String leadPhone;
    private String recording;
    private String date;
    private int duration;

    public LeadZvon() {}

    public LeadZvon(String text, String operatorName, int operatorExtension, String leadName, String leadPhone, String recording, String date, int duration) {
        this.text = text;
        this.operatorName = operatorName;
        this.operatorExtension = operatorExtension;
        this.leadName = leadName;
        this.leadPhone = leadPhone;
        this.recording = recording;
        this.date = date;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public int getOperatorExtension() {
        return operatorExtension;
    }

    public void setOperatorExtension(int operatorExtension) {
        this.operatorExtension = operatorExtension;
    }

    public String getLeadName() {
        return leadName;
    }

    public void setLeadName(String leadName) {
        this.leadName = leadName;
    }

    public String getLeadPhone() {
        return leadPhone;
    }

    public void setLeadPhone(String leadPhone) {
        this.leadPhone = leadPhone;
    }

    public String getRecording() {
        return recording;
    }

    public void setRecording(String recording) {
        this.recording = recording;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
