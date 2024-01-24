package org.example.repo;

import org.example.model.HelpDeskIncidentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IHelpDeskIncidentTypeRepo extends JpaRepository<HelpDeskIncidentType, UUID> {
}
