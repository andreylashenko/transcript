package teamlead.transcript.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teamlead.transcript.repo.LeadZvonRepository;

@RequestMapping("metric")
@RestController
public class MetricController {

    private LeadZvonRepository leadZvonRepository;

    public MetricController(LeadZvonRepository leadZvonRepository) {
        this.leadZvonRepository = leadZvonRepository;
    }

    @GetMapping("recordCount")
    public Long recordCount() {
        return leadZvonRepository.count();
    }

    @GetMapping("todayRecordCount")
    public Long todayRecords() {
        return leadZvonRepository.findTodayRecords();
    }

    @GetMapping("totalDuration")
    public Long totalDuration() {
        return leadZvonRepository.getTotalDuration();
    }
}
