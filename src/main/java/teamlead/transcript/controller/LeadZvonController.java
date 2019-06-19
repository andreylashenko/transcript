package teamlead.transcript.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("leadzvon")
public class LeadZvonController {

    @PostMapping
    public String getDataFromLZ()
    {
        return null;
    }
}
