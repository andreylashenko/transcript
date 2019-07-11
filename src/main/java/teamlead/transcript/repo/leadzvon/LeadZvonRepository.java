package teamlead.transcript.repo.leadzvon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import teamlead.transcript.domain.LeadZvon;

@Repository
public interface LeadZvonRepository extends JpaRepository<LeadZvon, Long> {

    @Query("SELECT count(id) FROM LeadZvon as lz WHERE lz.date > CURRENT_DATE")
    Long findTodayRecords();

    @Query("SELECT SUM(duration) FROM LeadZvon")
    Long getTotalDuration();

}
