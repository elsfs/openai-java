package org.elsfs.openai.common;

import lombok.Data;

/**
 * @author zeng
 * @since 0.0.1
 */
@Data
public class CreateCompletionResponseUsage {

    private Integer prompt_tokens;
    private Integer completion_tokens;
    private  Number total_tokens;
}
