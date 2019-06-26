package teamlead.transcript.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import teamlead.transcript.repo.LeadZvonRepository;


@Service
public class LeadZvonService
{
    @Value("${ASTERISK_API_URL}")
    private String asteriskUrl;

    @Value("${ASTERISK_API_KEY}")
    private String asteriskKey;

    private LeadZvonRepository leadZvonRepository;
    private RestTemplate restTemplate = new RestTemplate();

    public LeadZvonService(LeadZvonRepository leadZvonRepository) {
        this.leadZvonRepository = leadZvonRepository;
    }

    public String getRecording(String leadPhone, int operatorExtension) throws JSONException
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject root = new JSONObject();
        JSONObject data = new JSONObject();

        data.put("api_key", asteriskKey);
        data.put("lead_phone", leadPhone);
        data.put("extension", operatorExtension);
        root.put("data", data);

        HttpEntity<JSONObject> entity = new HttpEntity<JSONObject>(root, headers);

        ResponseEntity<String> loginResponse = restTemplate.exchange(asteriskUrl, HttpMethod.POST, entity, String.class);

        if (loginResponse.getStatusCode() == HttpStatus.OK) {
            JSONObject userJson = new JSONObject(loginResponse.getBody());
            return userJson.getJSONObject("data").getJSONArray("urlrecord").toString();
        } else {
            return "";
        }
    }
}
