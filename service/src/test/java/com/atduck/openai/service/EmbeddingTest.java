package com.atduck.openai.service;

import com.atduck.openai.client.OpenAiApiConfig;
import com.atduck.openai.embedding.Embedding;
import com.atduck.openai.embedding.EmbeddingRequest;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;


public class EmbeddingTest {

    String token = System.getenv("OPENAI_TOKEN");
    String host = System.getenv("OPENAI_HOST");
    OpenAiService service = new OpenAiService(new OpenAiApiConfig(host, token, Duration.ofSeconds(10)));

    @Test
    void createEmbeddings() {
        EmbeddingRequest embeddingRequest = EmbeddingRequest.builder()
                .model("text-embedding-ada-002")
                .input(Collections.singletonList("The food was delicious and the waiter..."))
                .build();

        List<Embedding> embeddings = service.createEmbeddings(embeddingRequest).getData();

        assertFalse(embeddings.isEmpty());
        assertFalse(embeddings.get(0).getEmbedding().isEmpty());
    }
}
