package teamlead.transcript.repo.leadzvon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import teamlead.transcript.domain.LeadZvon;

import java.util.List;

@Repository
public interface LeadZvonRepository extends JpaRepository<LeadZvon, Long> {

    List<LeadZvon> findByTextContaining(String text);

    @Query("SELECT count(id) FROM LeadZvon as lz WHERE lz.date > CURRENT_DATE")
    Long findTodayRecords();

    @Query("SELECT SUM(duration) FROM LeadZvon")
    Long getTotalDuration();

}
