package teamlead.transcript.domain;

public class Asterisk {

    private String api_key;
    private int extension;
    private String leadPhone;
    private String DateStart;
    private String DataEnd;
    private String offset;

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public int getExtension() {
        return extension;
    }

    public void setExtension(int extension) {
        this.extension = extension;
    }

    public String getLeadPhone() {
        return leadPhone;
    }

    public void setLeadPhone(String leadPhone) {
        this.leadPhone = leadPhone;
    }

    public String getDateStart() {
        return DateStart;
    }

    public void setDateStart(String dateStart) {
        DateStart = dateStart;
    }

    public String getDataEnd() {
        return DataEnd;
    }

    public void setDataEnd(String dataEnd) {
        DataEnd = dataEnd;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "Asterisk{" +
                "apiKey='" + api_key + '\'' +
                ", extension=" + extension +
                ", leadPhone='" + leadPhone + '\'' +
                ", DateStart='" + DateStart + '\'' +
                ", DataEnd='" + DataEnd + '\'' +
                ", offset='" + offset + '\'' +
                '}';
    }
}
