package teamlead.transcript.domain;


import javax.persistence.*;

@Entity
@Table
public class LeadZvon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;
    private String operatorName;
    private int operatorExtension;
    private String leadName;
    private String leadPhone;
    private int date;
}
