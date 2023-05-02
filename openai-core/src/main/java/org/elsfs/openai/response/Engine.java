package org.elsfs.openai.response;

import lombok.Data;

/**
 * @author zeng
 * @since 0.0.1
 */
@Data
@Deprecated
public class Engine {
    private String id;
    private String object;
    private Number created;
    private Boolean ready;
}
