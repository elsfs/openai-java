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
public class CreateClassificationResponse {
    private String object;
    private String model;
    @JsonProperty("search_model")
    private String searchModel;
    private String completion;
    private String label;
    @JsonProperty("selected_examples")
    private List<CreateClassificationResponseSelectedExamplesInner> selectedExamples;

    @Data
    public static class CreateClassificationResponseSelectedExamplesInner {
        private Number document;
        private String text;
        private String label;

    }


}
