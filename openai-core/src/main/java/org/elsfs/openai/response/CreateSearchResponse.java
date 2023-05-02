package org.elsfs.openai.response;

import lombok.Data;

import java.util.Collection;
import java.util.List;

/**
 * @author zeng
 * @since 0.0.1
 */
@Data
public class CreateSearchResponse {
    private String object;
    private String model;
    private List<CreateSearchResponseDataInner> data;
    @Data
    public static class CreateSearchResponseDataInner {
        private String object;
        private Integer document;
        private  Integer score;
    }
}
