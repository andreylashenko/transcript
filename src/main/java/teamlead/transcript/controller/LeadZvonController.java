package teamlead.transcript.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import teamlead.transcript.domain.LeadZvon;
import teamlead.transcript.repo.LeadZvonRepository;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@RestController
@RequestMapping("leadzvon")
public class LeadZvonController {

    private LeadZvonRepository leadZvonRepository;
    private RestTemplate restTemplate = new RestTemplate();

    @Value("${ASTERISK_API_URL}")
    private String asteriskUrl;

    @Value("${ASTERISK_API_KEY}")
    private String asteriskKey;

    public LeadZvonController(LeadZvonRepository leadZvonRepository) {
        this.leadZvonRepository = leadZvonRepository;
    }

    @PostMapping
    public LeadZvon saveDataFromLZ(@RequestBody LeadZvon leadZvon) throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject data = new JSONObject();
        JSONObject data1 = new JSONObject();
        data1.put("api_key", asteriskKey);
        data.put("data", data1);

        HttpEntity<JSONObject> entity = new HttpEntity<JSONObject>(data, headers);



        ResponseEntity<String> loginResponse = restTemplate
                .exchange(asteriskUrl, HttpMethod.POST, entity, String.class);
        if (loginResponse.getStatusCode() == HttpStatus.OK) {
            JSONObject userJson = new JSONObject(loginResponse.getBody());
            System.out.println(userJson);
            leadZvon.setRecording(userJson.getJSONObject("data").getJSONArray("urlrecord").toString());
        } else if (loginResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            // nono... bad credentials
        }



        return leadZvonRepository.save(leadZvon);
    }

    @GetMapping
    public List<LeadZvon> getData()
    {



        return leadZvonRepository.findAll();
    }
}
