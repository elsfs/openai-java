package org.elsfs.openai.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.elsfs.openai.common.ChatCompletionResponseMessage;
import org.elsfs.openai.common.CreateChatCompletionResponseChoicesInner;
import org.elsfs.openai.common.CreateCompletionResponseUsage;

import java.util.Collection;
import java.util.List;

/**
 * @author zeng
 * @since 0.0.1
 */
@Data
public class CreateChatCompletionResponse {
    private String id;
    private String object;
    private String created;
    private String model;
    private List<CreateChatCompletionResponseChoicesInner> choices;
    private CreateCompletionResponseUsage usage;



}
