package org.elsfs.openai.response;

import lombok.Data;

/**
 * @author zeng
 * @since 0.0.1
 */
@Data
public class DeleteModelResponse {
    private String id;
    private String object;
    private Boolean deleted;
}
