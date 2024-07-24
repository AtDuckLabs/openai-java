package com.atduck.openai.service;

import com.atduck.openai.client.OpenAiApiConfig;
import com.atduck.openai.model.Model;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class ModelTest {

    String token = System.getenv("OPENAI_TOKEN");
    String host = System.getenv("OPENAI_HOST");
    OpenAiService service = new OpenAiService(new OpenAiApiConfig(host, token, Duration.ofSeconds(10)));

    @Test
    void listModels() {
        List<Model> models = service.listModels();

        assertFalse(models.isEmpty());
    }

    @Test
    void getModel() {
        Model model = service.getModel("babbage-002");

        assertEquals("babbage-002", model.id);
        assertEquals("system", model.ownedBy);
    }
}
