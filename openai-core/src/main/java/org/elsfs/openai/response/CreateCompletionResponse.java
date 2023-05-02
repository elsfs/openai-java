package org.elsfs.openai.response;

import lombok.Data;
import org.elsfs.openai.common.CreateChatCompletionResponseChoicesInner;
import org.elsfs.openai.common.CreateCompletionResponseUsage;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

/**
 * @author zeng
 * @since 0.0.1
 */
@Data
public class CreateCompletionResponse {
   private String id;
    private String object;
    private Instant created;
    private String model;
    private List<CreateChatCompletionResponseChoicesInner> choices;
    private CreateCompletionResponseUsage usage;
}
