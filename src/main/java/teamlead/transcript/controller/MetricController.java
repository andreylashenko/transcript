package teamlead.transcript.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teamlead.transcript.repo.leadzvon.LeadZvonRepository;

@RequestMapping("metric")
@RestController
public class MetricController {

    private LeadZvonRepository leadZvonRepository;

    public MetricController(LeadZvonRepository leadZvonRepository) {
        this.leadZvonRepository = leadZvonRepository;
    }

    @GetMapping("recordsCount")
    public Long recordsCount() {
        return leadZvonRepository.count();
    }

    @GetMapping("todayRecordsCount")
    public Long todayRecords() {
        return leadZvonRepository.findTodayRecords();
    }

    @GetMapping("totalDuration")
    public Long totalDuration() {
        return leadZvonRepository.getTotalDuration();
    }
}
