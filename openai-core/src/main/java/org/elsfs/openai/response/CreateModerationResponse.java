package org.elsfs.openai.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Collection;
import java.util.List;

/**
 * @author zeng
 * @since 0.0.1
 */
@Data
public class CreateModerationResponse {
    private String id;
    private String model;
    private List<CreateModerationResponseResultsInner> results;

    @Data
    public static class CreateModerationResponseResultsInner {
        private Boolean flagged;
        private CreateModerationResponseResultsInnerCategories categories;
        private CreateModerationResponseResultsInnerCategoryScores category_scores;

        @Data
        public static class CreateModerationResponseResultsInnerCategories {
            private Boolean hate;
            @JsonProperty("hate/threatening")
            private Boolean hateThreatening;
            @JsonProperty("self-harm")
            private Boolean selfHarm;
            private Boolean sexual;
            @JsonProperty("sexual/minors")
            private Boolean sexualMinors;
            private Boolean violence;
            @JsonProperty("violence/graphic")
            private Boolean violenceGraphic;
        }

        @Data
        public static class CreateModerationResponseResultsInnerCategoryScores {
            private Number hate;
            @JsonProperty("hate/threatening")
            private Number hateThreatening;
            @JsonProperty("self-harm")
            private Number selfHarm;
            private Number sexual;
            @JsonProperty("sexual/minors")
            private Number sexualMinors;
            private Number violence;
            @JsonProperty("violence/graphic")
            private Number violenceGraphic;
        }

    }


}
