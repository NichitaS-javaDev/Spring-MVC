package org.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.UUID;

@Entity
@Getter
public class HelpDeskIncidentScope {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Size(min = 5)
    private String name;
    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private HelpDeskIncidentOrigin origin;
}
