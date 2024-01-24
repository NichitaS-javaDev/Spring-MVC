package org.example.controller;

import org.example.model.HelpDeskIncident;
import org.example.repo.IHelpDeskIncidentOriginRepo;
import org.example.repo.IHelpDeskIncidentRepo;
import org.example.repo.IHelpDeskIncidentScopeRepo;
import org.example.repo.IHelpDeskIncidentTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.NoSuchElementException;
import java.util.UUID;

@Controller
public class HelpDeskIncidentController {
    private final IHelpDeskIncidentRepo incidentRepo;
    private final IHelpDeskIncidentTypeRepo typeRepo;
    private final IHelpDeskIncidentScopeRepo scopeRepo;
    private final IHelpDeskIncidentOriginRepo originRepo;

    @Autowired
    public HelpDeskIncidentController(
            IHelpDeskIncidentRepo incidentRepo, IHelpDeskIncidentTypeRepo typeRepo, IHelpDeskIncidentScopeRepo scopeRepo, IHelpDeskIncidentOriginRepo originRepo
    ) {
        this.incidentRepo = incidentRepo;
        this.typeRepo = typeRepo;
        this.scopeRepo = scopeRepo;
        this.originRepo = originRepo;
    }

    @GetMapping("/")
    public String incidents(Model model) {
        model.addAttribute("incidentList", incidentRepo.findAll());

        return "incidents";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("typeList", typeRepo.findAll());
        model.addAttribute("incident", new HelpDeskIncident());

        return "addIncident";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("incident") HelpDeskIncident incident){
        incidentRepo.save(incident);

        return "redirect:/";
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
