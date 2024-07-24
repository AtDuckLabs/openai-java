package com.atduck.openai.service;

import com.atduck.openai.OpenAiHttpException;
import com.atduck.openai.client.OpenAiApiConfig;
import com.atduck.openai.edit.EditRequest;
import com.atduck.openai.edit.EditResult;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EditTest {


    String token = System.getenv("OPENAI_TOKEN");
    String host = System.getenv("OPENAI_HOST");
    OpenAiService service = new OpenAiService(new OpenAiApiConfig(host, token, Duration.ofSeconds(10)));

    @Test
    void edit() throws OpenAiHttpException {
        EditRequest request = EditRequest.builder()
                .model("text-davinci-edit-001")
                .input("What day of the wek is it?")
                .instruction("Fix the spelling mistakes")
                .build();

        EditResult result = service.createEdit(request);
        assertNotNull(result.getChoices().get(0).getText());
    }
}
