package org.example.repo;

import org.example.model.HelpDeskIncidentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IHelpDeskIncidentTypeRepo extends JpaRepository<HelpDeskIncidentType, UUID> {
    HelpDeskIncidentType findByName(String name);
}
