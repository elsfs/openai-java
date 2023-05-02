package org.elsfs.openai.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zeng
 * @since 0.0.1
 */
@Getter
@AllArgsConstructor
public enum ChatCompletionRequestMessageRoleEnum {
    system("system"),
    user("user"),
    assistant("assistant");
    private final String value;

}