package teamlead.transcript.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teamlead.transcript.domain.LeadZvon;

import java.util.List;

@Repository
public interface LeadZvonRepository extends JpaRepository<LeadZvon, Long> {

    List<LeadZvon> findByTextContaining(String text);

}
