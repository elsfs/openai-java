package org.elsfs.openai.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.elsfs.openai.request.ChatCompletionResponseMessage;

import java.util.List;

/**
 * @author zeng
 * @since 0.0.1
 */
@Data
public class CreateEditResponse {
    private String id;
    private String object;
    private String created;
    private String model;
    private List<CreateChatCompletionResponseChoicesInner> choices;
    private CreateCompletionResponseUsage usage;

    @Data
    public static class CreateCompletionResponseUsage {
        @JsonProperty("prompt_tokens")
        private Integer promptTokens;
        @JsonProperty("completion_tokens")
        private Integer completionTokens;
        @JsonProperty("total_tokens")
        private Number totalTokens;
    }

    @Data
    public static class CreateChatCompletionResponseChoicesInner {
        private Integer index;
        private ChatCompletionResponseMessage message;
        @JsonProperty("finish_reason")
        private String finishReason;
    }

}
