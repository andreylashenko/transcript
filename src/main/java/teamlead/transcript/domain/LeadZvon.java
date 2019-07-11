package teamlead.transcript.domain;


import javax.persistence.*;
import java.util.Date;

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
    private Date date;
    private int duration;
    private String leadExternalId;
    private String operatorId;

    public LeadZvon() {}

    public LeadZvon(
            String text,
            String operatorName,
            int operatorExtension,
            String leadName,
            String leadPhone,
            String recording,
            Date date,
            int duration,
            String leadExternalId,
            String operatorId
    ) {
        this.text = text;
        this.operatorName = operatorName;
        this.operatorExtension = operatorExtension;
        this.leadName = leadName;
        this.leadPhone = leadPhone;
        this.recording = recording;
        this.date = date;
        this.duration = duration;
        this.leadExternalId = leadExternalId;
        this.operatorId = operatorId;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getLeadExternalId() {
        return leadExternalId;
    }

    public void setLeadExternalId(String leadExternalId) {
        this.leadExternalId = leadExternalId;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }
}
