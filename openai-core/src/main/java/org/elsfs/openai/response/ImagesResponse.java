package org.elsfs.openai.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


import java.time.Instant;
import java.util.List;

/**
 * @author zeng
 * @since 0.0.1
 */
@Data
public class ImagesResponse {
    private Instant created;
    private List<ImagesResponseDataInner> data;
    @Data
    public static class ImagesResponseDataInner {
        private String url;
        @JsonProperty("b64_json")
        private String b64Json;
    }

}
