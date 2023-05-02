package org.elsfs.openai.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author zeng
 * @since 0.0.1
 */
@Data
public class CreateEmbeddingResponse {
    private String object;
    private String model;
    private List<CreateEmbeddingResponseDataInner> data;
    private CreateEmbeddingResponseUsage usage;

    @Data
    public static class CreateEmbeddingResponseDataInner {
        private String index;
        private String object;
        private List<Number> embedding;
    }

    @Data
    public static class CreateEmbeddingResponseUsage {
        @JsonProperty("prompt_tokens")
        private Long promptTokens;
        @JsonProperty("total_tokens")
        private Long totalTokens;
    }

}
