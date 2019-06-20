package teamlead.transcript.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import teamlead.transcript.domain.LeadZvon;

public interface LeadZvonRepository extends JpaRepository<LeadZvon, Long> {
}
