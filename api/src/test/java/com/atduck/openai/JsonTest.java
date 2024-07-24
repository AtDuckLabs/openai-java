package com.atduck.openai;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.*;
import com.atduck.openai.audio.TranscriptionResult;
import com.atduck.openai.audio.TranslationResult;
import com.atduck.openai.completion.chat.ChatCompletionRequest;
import com.atduck.openai.completion.chat.ChatCompletionResult;
import com.atduck.openai.edit.EditRequest;
import com.atduck.openai.edit.EditResult;
import com.atduck.openai.embedding.EmbeddingRequest;
import com.atduck.openai.embedding.EmbeddingResult;
import com.atduck.openai.engine.Engine;
import com.atduck.openai.file.File;
import com.atduck.openai.fine_tuning.FineTuningEvent;
import com.atduck.openai.fine_tuning.FineTuningJob;
import com.atduck.openai.fine_tuning.FineTuningJobRequest;
import com.atduck.openai.finetune.FineTuneEvent;
import com.atduck.openai.finetune.FineTuneResult;
import com.atduck.openai.image.ImageResult;
import com.atduck.openai.messages.Message;
import com.atduck.openai.model.Model;
import com.atduck.openai.moderation.ModerationRequest;
import com.atduck.openai.moderation.ModerationResult;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {

    @ParameterizedTest
    @ValueSource(classes = {
            ChatCompletionRequest.class,
            ChatCompletionResult.class,
            DeleteResult.class,
            EditRequest.class,
            EditResult.class,
            EmbeddingRequest.class,
            EmbeddingResult.class,
            Engine.class,
            File.class,
            FineTuneEvent.class,
            FineTuneResult.class,
            FineTuningEvent.class,
            FineTuningJob.class,
            FineTuningJobRequest.class,
            ImageResult.class,
            TranscriptionResult.class,
            TranslationResult.class,
            Message.class,
            Model.class,
            ModerationRequest.class,
            ModerationResult.class
    })
    void objectMatchesJson(Class<?> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        String path = "src/test/resources/fixtures/" + clazz.getSimpleName() + ".json";
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        String json = new String(bytes);

        String actual = mapper.writeValueAsString(mapper.readValue(json, clazz));

        // Convert to JsonNodes to avoid any json formatting differences
        assertEquals(mapper.readTree(json), mapper.readTree(actual));
    }
}
