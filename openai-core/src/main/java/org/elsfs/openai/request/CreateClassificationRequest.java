package org.elsfs.openai.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author zeng
 * @since 0.0.1
 */
@Data
@Accessors(chain = true)
public class CreateClassificationRequest {
    /**
     * ID of the model to use. You can use the [List models](/docs/api-reference/models/list) API to see all of your available models, or see our [Model overview](/docs/models/overview) for descriptions of them.
     * 要使用的模型的ID。您可以使用[List models]（/docs/api-reference/models/List）api查看所有可用的模型，也可以查看我们的[Model overview]（/docs/models/overview）以了解它们的描述。
     */
    private String model;
    /**
     * Query to be classified.
     * 要分类的查询。
     */
    private String query;
    /**
     *
     */
    private List<String> examples;
    private String file;
    /**
     * * The set of categories being classified. If not specified, candidate labels will be automatically collected from the examples you provide. All the label strings will be normalized to be capitalized.
     */
    private String labels;
    /**
     * * ID of the model to use for [Search](/docs/api-reference/searches/create). You can select one of `ada`, `babbage`, `curie`, or `davinci`.
     */
    @JsonProperty("search_model")
    private String searchModel;
    private Number temperature;
    /**
     * Include the log probabilities on the `logprobs` most likely tokens, as well the chosen tokens. For example, if `logprobs` is 5, the API will return a list of the 5 most likely tokens. The API will always return the `logprob` of the sampled token, so there may be up to `logprobs+1` elements in the response.  The maximum value for `logprobs` is 5. If you need more than this, please contact us through our [Help center](https://help.openai.com) and describe your use case.  When `logprobs` is set, `completion` will be automatically added into `expand` to get the logprobs.
     */
    private Number logprobs;
    @JsonProperty("max_examples")
    private Number maxExamples;
    @JsonProperty("logit_bias")
    private Map<String, Object> logitBias;
    @JsonProperty("return_prompt")
    private Boolean returnPrompt;
    @JsonProperty("return_metadata")
    private Boolean returnMetadata;
    /**
     * If an object name is in the list, we provide the full information of the object; otherwise, we only provide the object ID. Currently we support `completion` and `file` objects for expansion.
     */
    private List<String> expand;
    private String user;

}
