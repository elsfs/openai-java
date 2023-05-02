package org.elsfs.openai.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.nashorn.internal.objects.annotations.Property;
import lombok.Data;

import java.util.Collection;
import java.util.List;

/**
 * @author zeng
 * @since 0.0.1
 */
@Data
public class CreateAnswerResponse {
    private String object;
    private String model;
    @JsonProperty("search_model")
    private String searchModel;
    private String completion;
    private List<String> answers;
    @JsonProperty("selected_documents")
    private List<CreateAnswerResponseSelectedDocumentsInner> selectedDocuments;

    @Data
    public static class CreateAnswerResponseSelectedDocumentsInner {
        private Number document;
        private String text;
    }
}
