package teamlead.transcript.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import teamlead.transcript.domain.Message;

public interface MessageRepo extends JpaRepository<Message, Long> {
}
