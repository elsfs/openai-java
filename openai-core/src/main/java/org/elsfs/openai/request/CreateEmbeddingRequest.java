package org.elsfs.openai.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collection;
import java.util.List;

/**
 * @author zeng
 * @since 0.0.1
 */
@Data
@Accessors(chain = true)
public class CreateEmbeddingRequest {
    /**
     * ID of the model to use. You can use the [List models](/docs/api-reference/models/list) API to see all of your available models,
     * or see our [Model overview](/docs/models/overview) for descriptions of them.
     */
    private String model;
    private List<String> input;
    private String user;
}
