package org.elsfs.openai.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.Instant;
import java.util.Collection;

/**
 * @author zeng
 * @since 0.0.1
 */
@Data
public class FineTune {
    private String id;
    private String object;
    @JsonProperty("created_at")
    private Instant createdAt;
    @JsonProperty("updated_at")
    private Instant updatedAt;
    private String model;
    @JsonProperty("fine_tuned_model")
    private String fineTunedModel;
    @JsonProperty("organization_id")
    private String organizationId;
    private String status;
    private String hyperparams;
    @JsonProperty("training_files")
    private Collection<OpenAIFile> training_files;
    @JsonProperty("validation_files")
    private Collection<OpenAIFile> validationFiles;
    @JsonProperty("result_files")
    private Collection<OpenAIFile> resultFiles;
    private Collection<FineTuneEvent> events;

    @Data
    public static class FineTuneEvent {
        private String object;
        @JsonProperty("created_at")
        private Instant createdAt;
        private String level;
        private String message;
    }

}
