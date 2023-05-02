package org.elsfs.openai.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

/**
 * 获取模型列表
 * @author zeng
 * @since 0.0.1
 */
@Data
public class ListModelsResponse implements Serializable {
    private String object;
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<Model> data;

    @Data
    public static class Model {
        String id;
        String object;
        Instant created;
        @JsonProperty("owned_by")
        String ownedBy;
        @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
        List<Permission> permission;
        String root;
        String parent;

        @Data
        public static class Permission {
            String id;
            @JsonProperty("model_permission")
            String modelPermission;
            Instant created;
            @JsonProperty("allow_create_engine")
            Boolean allowCreateEngine;
            @JsonProperty("allow_sampling")
            Boolean allowSampling;
            @JsonProperty("allow_logprobs")
            Boolean allowLogprobs;
            @JsonProperty("allow_search_indices")
            Boolean allowSearchIndices;
            @JsonProperty("allow_view")
            Boolean allowView;
            @JsonProperty("allow_fine_tuning")
            Boolean allowFineTuning;
            String organization;
            String group;
            @JsonProperty("is_blocking")
            Boolean isBlocking;
        }
    }

}
