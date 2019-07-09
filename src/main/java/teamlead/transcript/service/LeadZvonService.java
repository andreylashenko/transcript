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
import teamlead.transcript.repo.LeadZvonRepository;
import java.io.IOException;
import java.util.HashMap;

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

    public HashMap getRecording(String leadPhone, int operatorExtension) throws JSONException, IOException {

        Asterisk asterisk = new Asterisk();
        asterisk.setApi_key(asteriskKey);
        asterisk.setDateStart("1970-01-01 00:00:00");
        asterisk.setDataEnd("2019-06-27 11:37:16");
        asterisk.setExtension(operatorExtension);
        asterisk.setOffset("0");
        asterisk.setLeadPhone(leadPhone);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = Obj.writeValueAsString(asterisk);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("data", jsonStr);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
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
}

