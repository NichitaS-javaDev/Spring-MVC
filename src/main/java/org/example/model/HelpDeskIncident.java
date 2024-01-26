package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import jdk.jfr.Description;
import lombok.Getter;
import lombok.Setter;
import org.example.annotation.GenerateUUID;
import org.example.json.HelpDeskIncidentTypeByNameDeserializer;
import org.example.listener.GenerateUUIDListener;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@EntityListeners({AuditingEntityListener.class, GenerateUUIDListener.class})
public class HelpDeskIncident extends BaseAuditEntity {
    @GenerateUUID
    private UUID requestNr;
    @JsonProperty
    @Size(min = 5)
    private String subsystem;
    @JsonProperty
    @FutureOrPresent
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date openDate;
    @JsonProperty
    @FutureOrPresent
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date closeDate;
    @Min(1)
    @Max(5)
    @JsonProperty
    @Description("Where 1 is higher urgency level and 5 the lower")
    private short urgency;
    @JsonProperty
    @Size(min = 10)
    private String problemSummary;
    @JsonProperty
    @Size(min = 10)
    private String problemDescription;
    @JsonProperty
    @JsonDeserialize(using = HelpDeskIncidentTypeByNameDeserializer.class)
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(referencedColumnName = "id")
    private HelpDeskIncidentType incidentType;

}
