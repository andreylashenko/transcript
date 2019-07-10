package teamlead.transcript.controller;

import org.json.JSONException;
import org.springframework.web.bind.annotation.*;
import teamlead.transcript.domain.LeadZvon;
import teamlead.transcript.repo.LeadZvonRepository;
import teamlead.transcript.service.LeadZvonService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
    public LeadZvon saveDataFromLZ(@RequestBody LeadZvon leadZvon) throws IOException, JSONException, ParseException {
        HashMap<String, String> recordings = leadZvonService.getRecording(leadZvon.getLeadPhone(), leadZvon.getOperatorExtension());

        leadZvon.setRecording(recordings.get("urlrecord"));
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(recordings.get("calldate"));
        leadZvon.setDate(date);
        leadZvon.setDuration(Integer.parseInt(recordings.get("duration")));

        return leadZvonRepository.save(leadZvon);
    }

    @GetMapping("/{words}")
    public List<LeadZvon> getData(@PathVariable String words) throws UnsupportedEncodingException {
        String str = URLDecoder.decode( words, "UTF-8" );
        return leadZvonRepository.findByTextContaining(str);
    }

    @GetMapping("/list")
    public List<LeadZvon> list() {
        return leadZvonRepository.findAll();
    }
}
