package org.example.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.example.model.HelpDeskIncidentType;
import org.example.repo.IHelpDeskIncidentTypeRepo;

import java.io.IOException;

public class HelpDeskIncidentTypeByNameDeserializer extends JsonDeserializer<HelpDeskIncidentType> {
    @Override
    public HelpDeskIncidentType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        IHelpDeskIncidentTypeRepo incidentTypeRepo =
                (IHelpDeskIncidentTypeRepo) deserializationContext.findInjectableValue(IHelpDeskIncidentTypeRepo.class.getCanonicalName(), null, null);
        return incidentTypeRepo.findByName(jsonParser.getText());
    }
}
