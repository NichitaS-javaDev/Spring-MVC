package org.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import jdk.jfr.Description;
import lombok.Getter;

import java.util.Date;

@Entity
@Getter
public class HelpDeskIncident extends BaseAuditEntity {
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long requestNr;
    @Size(min = 5)
    private String subsystem;
    @Temporal(TemporalType.DATE)
    private Date openDate;
    @Temporal(TemporalType.DATE)
    private Date closeDate;
    @Min(1)
    @Max(5)
    @Description("Where 1 is higher urgency level and 5 the lower")
    private short urgency;
    @Size(min = 10)
    private String problemSummary;
    @Size(min = 10)
    private String problemDescription;
    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private HelpDeskIncidentType incidentType;

}
