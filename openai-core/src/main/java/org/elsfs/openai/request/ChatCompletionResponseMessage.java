package org.elsfs.openai.request;


import lombok.Data;
import org.elsfs.openai.common.ChatCompletionRequestMessageRoleEnum;

/**
 * @author zeng
 * @since 0.0.1
 */
@Data
public class ChatCompletionResponseMessage {
    private ChatCompletionRequestMessageRoleEnum role;
    private String content;
}
