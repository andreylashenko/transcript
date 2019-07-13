package teamlead.transcript.dto;

public class Filter {

    private String words;
    private String operatorId;
    private String leadExternalId;
    private String leadPhone;
    private String dateStart;
    private String dateEnd;

    public Filter() {
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getLeadExternalId() {
        return leadExternalId;
    }

    public void setLeadExternalId(String leadExternalId) {
        this.leadExternalId = leadExternalId;
    }

    public String getLeadPhone() {
        return leadPhone;
    }

    public void setLeadPhone(String leadPhone) {
        this.leadPhone = leadPhone;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }
}
