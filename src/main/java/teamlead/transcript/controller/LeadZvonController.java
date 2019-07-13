package teamlead.transcript.controller;

import org.json.JSONException;
import org.springframework.web.bind.annotation.*;
import teamlead.transcript.domain.LeadZvon;
import teamlead.transcript.dto.Filter;
import teamlead.transcript.repo.leadzvon.LeadZvonRepository;
import teamlead.transcript.service.LeadZvonService;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("leadzvon")
public class LeadZvonController {

    private LeadZvonRepository leadZvonRepository;
    private LeadZvonService leadZvonService;
    private EntityManager em;

    public LeadZvonController(LeadZvonRepository leadZvonRepository, LeadZvonService leadZvonService, EntityManager em) {
        this.leadZvonRepository = leadZvonRepository;
        this.leadZvonService = leadZvonService;
        this.em = em;
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

    @PostMapping("/list")
    public List<LeadZvon> list(@RequestBody Filter filter) throws UnsupportedEncodingException, ParseException {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<LeadZvon> cq = cb.createQuery(LeadZvon.class);

        Root<LeadZvon> leadZvon = cq.from(LeadZvon.class);
        List<Predicate> predicates = new ArrayList<>();

        if(filter.getWords() != null && !filter.getWords().isEmpty()) {
            predicates.add(cb.like(leadZvon.get("text"), "%" + URLDecoder.decode(filter.getWords(), "UTF-8" ) + "%"));
        }

        if(filter.getOperatorId() != null && !filter.getOperatorId().isEmpty()) {
            predicates.add(cb.equal(leadZvon.get("operatorId"), filter.getOperatorId()));
        }

        if(filter.getLeadPhone() != null && !filter.getLeadPhone().isEmpty()) {
            predicates.add(cb.equal(leadZvon.get("leadPhone"), filter.getLeadPhone()));
        }

        if(filter.getLeadExternalId() != null && !filter.getLeadExternalId().isEmpty()) {
            predicates.add(cb.equal(leadZvon.get("leadExternalId"), filter.getLeadExternalId()));
        }

        if(filter.getDateStart() != null && !filter.getDateStart().isEmpty()) {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(filter.getDateStart());

            predicates.add(cb.greaterThan(leadZvon.get("date").as(Date.class), date));
        }

        if(filter.getDateEnd() != null && !filter.getDateEnd().isEmpty()) {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(filter.getDateEnd());

            predicates.add(cb.lessThan(leadZvon.get("date").as(Date.class), date));
        }

        cq.where(predicates.toArray(new Predicate[0]));


        return em.createQuery(cq).getResultList();
    }
}
