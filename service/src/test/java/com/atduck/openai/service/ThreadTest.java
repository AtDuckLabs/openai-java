package com.atduck.openai.service;

import com.atduck.openai.DeleteResult;
import com.atduck.openai.client.OpenAiApiConfig;
import com.atduck.openai.messages.MessageRequest;
import com.atduck.openai.threads.Thread;
import com.atduck.openai.threads.ThreadRequest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ThreadTest {

    String token = System.getenv("OPENAI_TOKEN");
    String host = System.getenv("OPENAI_HOST");
    OpenAiService service = new OpenAiService(new OpenAiApiConfig(host, token, Duration.ofSeconds(10)));

    static String threadId;

    @Test
    @Order(1)
    void createThread() {
        MessageRequest messageRequest = MessageRequest.builder()
                .content("Hello")
                .build();

        ThreadRequest threadRequest = ThreadRequest.builder()
                .messages(Collections.singletonList(messageRequest))
                .build();

        Thread thread = service.createThread(threadRequest);
        threadId = thread.getId();
        assertEquals("thread", thread.getObject());
    }

    @Test
    @Order(2)
    void retrieveThread() {
        Thread thread = service.retrieveThread(threadId);
        System.out.println(thread.getMetadata());
        assertEquals("thread", thread.getObject());
    }

    @Test
    @Order(3)
    void modifyThread() {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("action", "modify");
        ThreadRequest threadRequest = ThreadRequest.builder()
                .metadata(metadata)
                .build();
        Thread thread = service.modifyThread(threadId, threadRequest);
        assertEquals("thread", thread.getObject());
        assertEquals("modify", thread.getMetadata().get("action"));
    }

    @Test
    @Order(4)
    void deleteThread() {
        DeleteResult deleteResult = service.deleteThread(threadId);
        assertEquals("thread.deleted", deleteResult.getObject());
    }
}