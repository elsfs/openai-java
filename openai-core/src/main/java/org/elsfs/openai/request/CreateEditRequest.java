package org.elsfs.openai.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zeng
 * @since 0.0.1
 */
@Data
@Accessors(chain = true)
public class CreateEditRequest extends CommonRequest {
    /**
     * ID of the model to use. You can use the `text-davinci-edit-001` or `code-davinci-edit-001` model with this endpoint.
     */
    private String model;
    /**
     * optional
     * The input text to use as a starting point for the edit.
     */
    private String input;
    /**
     * The instruction that tells the model how to edit the prompt.
     */
    private String instruction;
    @JsonProperty("top_p")
    private String topP;

}
