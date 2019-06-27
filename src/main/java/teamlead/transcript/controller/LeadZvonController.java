package teamlead.transcript.controller;

import org.json.JSONException;
import org.springframework.web.bind.annotation.*;
import teamlead.transcript.domain.LeadZvon;
import teamlead.transcript.repo.LeadZvonRepository;
import teamlead.transcript.service.LeadZvonService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@RestController
@RequestMapping("leadzvon")
public class LeadZvonController {

    private LeadZvonRepository leadZvonRepository;
    private LeadZvonService leadZvonService;

    public LeadZvonController(
            LeadZvonRepository leadZvonRepository,
            LeadZvonService leadZvonService
    ) {
        this.leadZvonRepository = leadZvonRepository;
        this.leadZvonService = leadZvonService;
    }

    @PostMapping
    public LeadZvon saveDataFromLZ(@RequestBody LeadZvon leadZvon)
    {
        String record = null;
        try {
            try {
                record = leadZvonService.getRecording(leadZvon.getLeadPhone(), leadZvon.getOperatorExtension());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (JSONException e) {
            new RuntimeException("Cannot get record");
        }
        leadZvon.setRecording(record);
        return leadZvonRepository.save(leadZvon);
    }

    @GetMapping("/{words}")
    public List<LeadZvon> getData(@PathVariable String words) throws UnsupportedEncodingException {
        String str = URLDecoder.decode( words, "UTF-8" );
        return leadZvonRepository.findByTextContaining(str);
    }
}
