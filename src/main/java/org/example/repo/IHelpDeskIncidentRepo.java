package org.example.repo;

import org.example.model.HelpDeskIncident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IHelpDeskIncidentRepo extends JpaRepository<HelpDeskIncident, UUID> {
}
