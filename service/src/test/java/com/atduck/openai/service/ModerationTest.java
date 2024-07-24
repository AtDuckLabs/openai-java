package com.atduck.openai.service;

import com.atduck.openai.client.OpenAiApiConfig;
import com.atduck.openai.moderation.Moderation;
import com.atduck.openai.moderation.ModerationRequest;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class ModerationTest {

    String token = System.getenv("OPENAI_TOKEN");
    String host = System.getenv("OPENAI_HOST");
    OpenAiService service = new OpenAiService(new OpenAiApiConfig(host, token, Duration.ofSeconds(10)));

    @Test
    void createModeration() {
        ModerationRequest moderationRequest = ModerationRequest.builder()
                .input("I want to kill him")
                .model("text-moderation-latest")
                .build();

        Moderation moderationScore = service.createModeration(moderationRequest).getResults().get(0);

        assertTrue(moderationScore.isFlagged());
    }
}
