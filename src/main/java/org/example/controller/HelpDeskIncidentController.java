package org.example.controller;

import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.example.model.HelpDeskIncident;
import org.example.repo.IHelpDeskIncidentRepo;
import org.example.repo.IHelpDeskIncidentTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Controller
@SessionAttributes({"incident", "operation"})
public class HelpDeskIncidentController {
    private final IHelpDeskIncidentRepo incidentRepo;
    private final IHelpDeskIncidentTypeRepo typeRepo;

    private enum Operations {
        CREATE,
        UPDATE
    }

    @Autowired
    public HelpDeskIncidentController(IHelpDeskIncidentRepo incidentRepo, IHelpDeskIncidentTypeRepo typeRepo) {
        this.incidentRepo = incidentRepo;
        this.typeRepo = typeRepo;
    }

    @GetMapping("/")
    public String incidents(Model model) {
        model.addAttribute("incidentList", incidentRepo.findAll());

        return "incidents";
    }

    @GetMapping("/createUpdate")
    public String create(Model model) {
        model.addAttribute("typeList", typeRepo.findAll());
        model.addAttribute("incident", new HelpDeskIncident());
        model.addAttribute("operation", Operations.CREATE.name());

        return "createUpdateIncident";
    }

    @GetMapping("/createUpdate/{id}")
    public String createUpdate(@PathVariable(value = "id") UUID id, Model model) {
        HelpDeskIncident incident = incidentRepo.findById(id).orElseThrow();
        model.addAttribute("typeList", typeRepo.findAll());
        model.addAttribute("incident", incident);
        model.addAttribute("operation", Operations.UPDATE.name());

        return "createUpdateIncident";
    }

    @PostMapping("/createUpdate")
    public String createUpdate(@ModelAttribute("incident") @Valid HelpDeskIncident incident) {
        incidentRepo.save(incident);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") UUID id) {
        incidentRepo.deleteById(id);

        return "redirect:/";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") UUID id, Model model) {
        try {
            HelpDeskIncident incident = incidentRepo.findById(id).orElseThrow();
            model.addAttribute("incident", incident);
        } catch (NoSuchElementException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "details";
    }

    @PostMapping("/import")
    public String importData(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                List<HelpDeskIncident> incidentList = new LinkedList<>();
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.setInjectableValues(new InjectableValues.Std().addValue(IHelpDeskIncidentTypeRepo.class, typeRepo));

                JsonNode node = objectMapper.readTree(new InputStreamReader(file.getInputStream()));
                Iterator<JsonNode> elements = node.elements();

                while (elements.hasNext()) {
                    JsonNode jsonNode = elements.next();
                    HelpDeskIncident incident = objectMapper.treeToValue(jsonNode, HelpDeskIncident.class);
                    incidentList.add(incident);

                    incidentRepo.saveAll(incidentList);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

        }

        return "redirect:/";
    }

}
