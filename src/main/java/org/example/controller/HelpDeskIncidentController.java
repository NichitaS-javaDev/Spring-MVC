package org.example.controller;

import org.example.model.HelpDeskIncident;
import org.example.repo.IHelpDeskIncidentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HelpDeskIncidentController {
    private final IHelpDeskIncidentRepo incidentRepo;

    @Autowired
    public HelpDeskIncidentController(IHelpDeskIncidentRepo incidentRepo) {
        this.incidentRepo = incidentRepo;
    }

    @ModelAttribute("incidentList")
    public List<HelpDeskIncident> getIncidentList(){
        return incidentRepo.findAll();
    }

    @GetMapping("/")
    public String incidents() {
        return "incidents";
    }
}
