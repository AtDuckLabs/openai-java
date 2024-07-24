package com.atduck.openai.service;

import com.atduck.openai.client.OpenAiApiConfig;
import com.atduck.openai.completion.CompletionChoice;
import com.atduck.openai.completion.CompletionChunk;
import com.atduck.openai.completion.CompletionRequest;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class CompletionTest {


    String token = System.getenv("OPENAI_TOKEN");
    String host = System.getenv("OPENAI_HOST");
    OpenAiService service = new OpenAiService(new OpenAiApiConfig(host, token, Duration.ofSeconds(10)));

    @Test
    void createCompletion() {
        CompletionRequest completionRequest = CompletionRequest.builder()
                .model("babbage-002")
                .prompt("Somebody once told me the world is gonna roll me")
                .echo(true)
                .n(5)
                .maxTokens(50)
                .user("testing")
                .logitBias(new HashMap<>())
                .logprobs(5)
                .build();

        List<CompletionChoice> choices = service.createCompletion(completionRequest).getChoices();
        assertEquals(5, choices.size());
        assertNotNull(choices.get(0).getLogprobs());
    }

    @Test
    void streamCompletion() {
        CompletionRequest completionRequest = CompletionRequest.builder()
                .model("babbage-002")
                .prompt("Somebody once told me the world is gonna roll me")
                .echo(true)
                .n(1)
                .maxTokens(25)
                .user("testing")
                .logitBias(new HashMap<>())
                .logprobs(5)
                .stream(true)
                .build();

        List<CompletionChunk> chunks = new ArrayList<>();
        service.streamCompletion(completionRequest).blockingForEach(chunks::add);
        assertTrue(chunks.size() > 0);
        assertNotNull(chunks.get(0).getChoices().get(0));
    }
}
