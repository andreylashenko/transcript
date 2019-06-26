package teamlead.transcript.controller;

import org.json.JSONException;
import org.springframework.web.bind.annotation.*;
import teamlead.transcript.domain.LeadZvon;
import teamlead.transcript.repo.LeadZvonRepository;
import teamlead.transcript.service.LeadZvonService;

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
            record = leadZvonService.getRecording(leadZvon.getLeadPhone(), leadZvon.getOperatorExtension());
        } catch (JSONException e) {
            new RuntimeException("Cannot get record");
        }
        leadZvon.setRecording(record);
        return leadZvonRepository.save(leadZvon);
    }

    @GetMapping
    public List<LeadZvon> getData()
    {
        return leadZvonRepository.findAll();
    }
}
