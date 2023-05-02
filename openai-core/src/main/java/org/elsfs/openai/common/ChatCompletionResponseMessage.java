package org.elsfs.openai.common;

import lombok.Data;
import org.elsfs.openai.response.CreateChatCompletionResponse;

/**
 * @author zeng
 * @since 0.0.1
 */
@Data
public class ChatCompletionResponseMessage {
    private ChatCompletionRequestMessageRoleEnum role;
    private String content;
}
