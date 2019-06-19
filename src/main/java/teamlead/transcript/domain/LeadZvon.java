package teamlead.transcript.domain;


import javax.persistence.*;

@Entity
@Table
public class LeadZvon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
