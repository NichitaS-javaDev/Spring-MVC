package org.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import jdk.jfr.Description;
import lombok.Getter;
import lombok.Setter;
import org.example.annotation.GenerateUUID;
import org.example.listener.GenerateUUIDListener;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@EntityListeners({AuditingEntityListener.class, GenerateUUIDListener.class})
public class HelpDeskIncident extends BaseAuditEntity {
    @GenerateUUID
    private UUID requestNr;
    @Size(min = 5)
    private String subsystem;
    @Temporal(TemporalType.DATE)
    private LocalDate openDate;
    @Temporal(TemporalType.DATE)
    private LocalDate closeDate;
    @Min(1)
    @Max(5)
    @Description("Where 1 is higher urgency level and 5 the lower")
    private short urgency;
    @Size(min = 10)
    private String problemSummary;
    @Size(min = 10)
    private String problemDescription;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(referencedColumnName = "id")
    private HelpDeskIncidentType incidentType;

}
