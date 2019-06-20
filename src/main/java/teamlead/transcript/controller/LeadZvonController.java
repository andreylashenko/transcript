package teamlead.transcript.controller;

import org.springframework.web.bind.annotation.*;
import teamlead.transcript.domain.LeadZvon;
import teamlead.transcript.repo.LeadZvonRepository;

import java.util.List;

@RestController
@RequestMapping("leadzvon")
public class LeadZvonController {

    private LeadZvonRepository leadZvonRepository;

    public LeadZvonController(LeadZvonRepository leadZvonRepository) {
        this.leadZvonRepository = leadZvonRepository;
    }

    @PostMapping
    public LeadZvon saveDataFromLZ(@RequestBody LeadZvon leadZvon)
    {
        return leadZvonRepository.save(leadZvon);
    }

    @GetMapping
    public List<LeadZvon> getData()
    {
        return leadZvonRepository.findAll();
    }
}
