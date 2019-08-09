package teamlead.transcript.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import teamlead.transcript.domain.Asterisk;
import teamlead.transcript.domain.LeadZvon;
import teamlead.transcript.repo.leadzvon.LeadZvonRepository;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class LeadZvonService
{
    @Value("${ASTERISK_API_URL}")
    private String asteriskUrl;

    @Value("${ASTERISK_API_KEY}")
    private String asteriskKey;

    private LeadZvonRepository leadZvonRepository;
    private RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public LeadZvonService(LeadZvonRepository leadZvonRepository) {
        this.leadZvonRepository = leadZvonRepository;
    }

    private HashMap setRecording(String leadPhone, int operatorExtension) throws JSONException, IOException {

        Date date = new Date();
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate= dateFormat.format(date);

        Asterisk asterisk = new Asterisk();
        asterisk.setDateStart("1970-01-01 00:00:00");
        asterisk.setDataEnd(formattedDate);
        asterisk.setExtension(operatorExtension);
        asterisk.setOffset("0");
        asterisk.setLeadPhone(leadPhone);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = Obj.writeValueAsString(asterisk);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("data", jsonStr);
        map.add("api_key", asteriskKey);

        HttpEntity<MultiValueMap> request = new HttpEntity<MultiValueMap>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity( asteriskUrl, request , String.class );

        JsonNode jsonNode = objectMapper.readTree(response.getBody());

        HashMap<String, String> data = new HashMap<>();

        for (JsonNode str : jsonNode.get("data")) {
            data.put("duration", str.get("duration").toString().replaceAll("\"", ""));
            data.put("calldate", str.get("calldate").toString().replaceAll("\"", ""));

            if (str.get("urlrecord").toString().isEmpty()) {
                data.put("urlrecord", str.get("recordingfile").toString().replaceAll("\"", ""));
            } else {
                data.put("urlrecord", str.get("urlrecord").toString().replaceAll("\"", ""));
            }
        }

        return data;
    }

    public void save(LeadZvon leadZvon) {
        try {
            HashMap<String, String> recordings = setRecording(leadZvon.getLeadPhone(), leadZvon.getOperatorExtension());
            leadZvon.setRecording(recordings.get("urlrecord"));
            leadZvon.setDuration(Integer.parseInt(recordings.get("duration")));
        } catch (Exception e) {
            leadZvon.setRecording("");
        }

        if(!leadZvon.getRecording().isEmpty()) {

            HashMap<String, String> scolding = new HashMap<>();
            scolding.put("х*", "хуй");
            scolding.put("п*", "пидор");
            scolding.put("с*", "сука");
            scolding.put("б*", "блядь");

            for (Map.Entry  str : scolding.entrySet()) {
                if(leadZvon.getText().contains(str.getKey().toString())) {
                    String newWord = leadZvon.getText().replace(str.getKey().toString(), str.getValue().toString());
                    leadZvon.setText(newWord);
                }
            }

            leadZvon.setText(leadZvon.getText().replace("*",""));
            leadZvon.setDate(new Date());
            leadZvonRepository.save(leadZvon);
        }
    }
}

