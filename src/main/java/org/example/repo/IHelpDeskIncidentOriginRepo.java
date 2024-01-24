package org.example.repo;

import org.example.model.HelpDeskIncidentOrigin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IHelpDeskIncidentOriginRepo extends JpaRepository<HelpDeskIncidentOrigin, UUID> {
}
