package org.elsfs.openai.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.Instant;

/**
 * @author zeng
 * @since 0.0.1
 */
@Data
public class OpenAIFile {
    private String id;
    private String object;
    private String bytes;
    @JsonProperty("created_at")
    private Instant createdAt;
    private String filename;
    private String purpose;
    private String status;
    @JsonProperty("status_details")
    private  String statusDetails;
}
