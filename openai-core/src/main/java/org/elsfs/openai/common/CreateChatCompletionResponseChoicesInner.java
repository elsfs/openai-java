package org.elsfs.openai.common;

import lombok.Data;

/**
 * @author zeng
 * @since 0.0.1
 */
@Data
public  class CreateChatCompletionResponseChoicesInner {
    private Integer index;
    private ChatCompletionResponseMessage message;
    private String finish_reason;
}
