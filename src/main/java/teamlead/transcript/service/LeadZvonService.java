package teamlead.transcript.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import teamlead.transcript.domain.Asterisk;
import teamlead.transcript.repo.LeadZvonRepository;

import java.io.IOException;


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

    public String getRecording(String leadPhone, int operatorExtension) throws JSONException, IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Asterisk asterisk = new Asterisk();
        asterisk.setApiKey(asteriskKey);
        asterisk.setDateStart("1970-01-01 00:00:00");
        asterisk.setDataEnd("2019-06-27 11:37:16");
        asterisk.setExtension(100);
        asterisk.setOffset("0");
        asterisk.setLeadPhone("1");

        HttpEntity<Asterisk> entity = new HttpEntity<Asterisk>(asterisk, headers);

        return restTemplate.exchange(asteriskUrl, HttpMethod.POST, entity, String.class).getBody();
    }
}
