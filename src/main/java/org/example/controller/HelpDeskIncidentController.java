package org.example.controller;

import org.example.model.HelpDeskIncident;
import org.example.repo.IHelpDeskIncidentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

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

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") UUID id, Model model){
        try {
            HelpDeskIncident incident = incidentRepo.findById(id).orElseThrow();
            model.addAttribute("incident", incident);
        } catch (NoSuchElementException e){
            model.addAttribute("error", e.getMessage());
        }

        return "details";
    }
}
