package org.example.controller;

import org.example.model.HelpDeskIncident;
import org.example.repo.IHelpDeskIncidentRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class HelpDeskIncidentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private IHelpDeskIncidentRepo incidentRepo;

    @Test
    public void testWelcome() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("incidentList"))
                .andExpect(MockMvcResultMatchers.view().name("incidents"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testCreate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/createUpdate"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("typeList"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("incident"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("operation"))
                .andExpect(MockMvcResultMatchers.view().name("createUpdateIncident"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testUpdate() throws Exception {
        HelpDeskIncident incident = incidentRepo.findAll().get(0);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/createUpdate/{id}", incident.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("typeList"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("incident"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("operation"))
                .andExpect(MockMvcResultMatchers.view().name("createUpdateIncident"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testCreateUpdate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/createUpdate").sessionAttr("incident", new HelpDeskIncident()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/"))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void testDelete() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/delete/{id}", UUID.randomUUID()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testDetails() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/details/{id}", UUID.randomUUID()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("error"))
                .andExpect(MockMvcResultMatchers.view().name("details"))
                .andDo(MockMvcResultHandlers.print());
    }

}
