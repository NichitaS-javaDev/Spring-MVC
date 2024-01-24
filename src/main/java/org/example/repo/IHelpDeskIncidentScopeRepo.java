package org.example.repo;

import org.example.model.HelpDeskIncidentScope;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IHelpDeskIncidentScopeRepo extends JpaRepository<HelpDeskIncidentScope, UUID> {
}
